package game.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ByteLookupTable;
import java.awt.image.LookupOp;
import game.IO;
import game.Logger;
import game.map.Coordinate;
import game.map.World;
import game.entity.Player;
import game.map.locations.*;

public class GameGUI extends JPanel implements Runnable
{
	//double buffer
    private Image dbImage;
    private Graphics dbg;
    
    public static final int FRAME_WIDTH = 800, FRAME_HEIGHT = 600;
    static final Dimension dim = new Dimension(FRAME_WIDTH, FRAME_HEIGHT);
    
    private Thread game;
    private volatile boolean running = false;
    private static boolean paused = false;
    private long period = 12*1000000;
    private static final int DELAYS_BEFORE_YIELD = 10;
    
    private World world = new World(new GenericLocation());
    private Player player = new Player();
    Camera camera = new Camera(world);
    HUD hud = new HUD(world);
    
    public GameGUI()
    {
        setPreferredSize(dim);
        setBackground(Color.black);
        setFocusable(true);
        addKeyListener(new IO());
        requestFocus();
        world.addPlayer(player,world.getCurrentLocation().getPreferredSpawn());
    }
    
    public void run()
    {
        long beforeTime, afterTime, diff, sleepTime, overSleepTime = 0;
        int delays = 0;
        while(running)
        {
            beforeTime = System.nanoTime();
            update();
            render();
            paintScreen();
//            requestFocus();
            
            afterTime = System.nanoTime();
            diff = afterTime - beforeTime;
            sleepTime = period - diff - overSleepTime;
            //if sleep time is from 0 to period, sleep
            if(sleepTime < period && sleepTime > 0)
            {
                try
                {
                    Thread.sleep(sleepTime/1000000L);
                    overSleepTime = 0;
                }
                catch(Exception e){}
            }
            //the diff was greater than period
            else if(diff > period)
                overSleepTime = diff - period;
            //count delays and yield after 10
            else if(++delays >= DELAYS_BEFORE_YIELD)
            {
                Thread.yield();
                delays = 0;
                overSleepTime = 0;
            }
            else
                overSleepTime = 0;
        }
    }
    
    public World getWorld()
    {
    	return world;
    }
    
    private void update()
	{
	    if(running && game != null)
	    {
	        world.update();
	    }
	}

	/**
     * called when the game becomes paused
     */
    private boolean pauseGame()
    {
        paused = true;
        System.out.println("paused!");
        return paused;
    }
    
    private boolean unpauseGame()
    {
        paused = false;
        System.out.println("unpaused!");
        return paused;
    }
    
    public boolean togglePause() 
    {
    	return (paused) ? unpauseGame() : pauseGame();
	}
    
    public void toggleMenu() 
    {
		
	}
    
    private void render()
    {
        if(dbImage == null)
            dbImage = createImage(FRAME_WIDTH, FRAME_HEIGHT);
        if(dbImage == null)
        {
            System.err.println("dbImage is null!");
            return;
        }
        else
        {
            dbg = dbImage.getGraphics();
        }
        dbg.setColor(Color.black);
        dbg.fillRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        
        draw(dbg);
    }
    
    public void draw(Graphics g)
    {
        world.draw(g);
        hud.draw(g);
        if(paused)
        {
        	@SuppressWarnings("unused")
			LookupOp LU;
    	    byte[] brightnessBloq = new byte[256];
            for(int i=0;i<256;i++)brightnessBloq[i]=(byte)(i/2);
            LU = new LookupOp( new ByteLookupTable(0,brightnessBloq), null);
            //g.drawImage();
        }
    }
    
    private void paintScreen()
    {
        Graphics g;
        try
        {
            g = this.getGraphics();
            if(dbImage != null && g != null)
            {
                g.drawImage(dbImage, 0, 0, null);
            }
            g.dispose();
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
    }
    public void addNotify()
    {
        super.addNotify();
        start();
    }
    
    private void start()
    {
        if(game == null || !running)
        {
            game = new Thread(this);
            game.start();
            running = true;
        }
    }
    
    public void stop()
    {
        if(running)
            running = false;
    }
    
}