package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class IO implements KeyListener
{
	public static boolean key_W = false, key_A = false, key_S = false, key_D = false;

	@Override
	public void keyPressed(KeyEvent e) 
	{
		switch(e.getKeyChar())
		{
			case(KeyEvent.VK_ESCAPE):
				Game.window.game.togglePause();
				break;
			case(KeyEvent.VK_SHIFT):
				Game.window.game.toggleMenu();
				Logger.log("TAB");
				break;
			case ('w'):
				key_W = true;
//				Logger.log("\'W\' key has been pressed!");
				break;
			case ('a'):
				key_A = true;
//				Logger.log("\'A\' key has been pressed!");
				break;
			case('s'):
				key_S = true;
//				Logger.log("\'S\' key has been pressed!");
				break;
			case('d'):
				key_D = true;
//				Logger.log("\'D\' key has been pressed!");
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		switch(e.getKeyChar())
		{
			case(KeyEvent.VK_TAB):
				Game.window.game.toggleMenu();
				break;
			case('w'):
				key_W = false;
//				Logger.log("\'W\' key has been released!");
				break;
			case('a'):
				key_A = false;
//				Logger.log("\'A\' key has been released!");
				break;
			case('s'):
				key_S = false;
//				Logger.log("\'S\' key has been released!");
				break;
			case('d'):
				key_D = false;
//				Logger.log("\'D\' key has been released!");
				break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) 
	{
		
	}

	public static BufferedImage readImage(String imagePath) 
	{
		try 
		{
			return ImageIO.read(new File(imagePath));
		} 
		catch (IOException e) 
		{
			Logger.err(e);
		}
		return null;
	}

}
