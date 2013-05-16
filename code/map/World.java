package game.map;
 
import game.entity.*;
import game.gui.GameGUI;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class World
{
    private Tile[][] map;
    Location currentLocation;
    Player player;
    public static final int PAN_UP = 0, PAN_DOWN = 1, PAN_LEFT = 2, PAN_RIGHT = 3;
    
    public boolean shouldMoveUp = true,shouldMoveDown = true,shouldMoveLeft = true,shouldMoveRight = true;
    
    public World(Location loc)
    {
        loadLocation(loc);
    }
    
    public void add(Living live, Coordinate coord)
    {
    	if(live instanceof Player)
    		addPlayer((Player)live, coord);
    	else if(live instanceof Animal)
			currentLocation.getAnimals().add((Animal) live);
    	else if(live instanceof NPC)
			currentLocation.getResidents().add((NPC) live);
    	else if(live instanceof Monster)
    		currentLocation.getMonsters().add((Monster) live);
    	live.spawn(this, coord);
    }
    
    public void addPlayer(Player player, Coordinate coord)
    {
    	this.player = player;
    	if(coord != null)
    		player.spawn(this, coord);
    	else
    		player.spawn(this, new Coordinate(0,0));
    	
    	
    }
    public Player getPlayer() 
	{
		return player;
	}

	/**
     * forms the tile array that is the map
     * TODO: load different maps from other classes of Locations
     */
    private void loadArray()
    {
        map = currentLocation.getMap();
    }
    
    public void loadLocation(Location loc)
    {
        currentLocation = loc;
        map = null;
        loadArray();
    }
    
    public Location getCurrentLocation()
    {
    	return currentLocation;
    }
    
    public void update()
    {
        player.update();
        checkShouldMove();
    }
    
    public ArrayList<Tile> getOccupiedSolidTiles(Rectangle mob) //returns array of occupied solid tiles
    {
        ArrayList<Tile> tiles = new ArrayList<Tile>();
        for(int row = 0; row < currentLocation.getNumRows(); row++)
        {
            for(int col = 0; col < currentLocation.getNumCols(); col++)
            {
                if(map[row][col].getType().isCollidable())
                {
                    if(mob.intersects(map[row][col]))
                    {
                        tiles.add(map[row][col]);
                    }
                }
            }
        }
        return tiles;
    }
    
    /**
     * looks for the Tile that contains the same coordinates as the center of the Rectangle given
     * @param Rectangle of Mob to find Tile for
     * @return Tile that the mob is mostly on
     */
    public Tile getTileCenteredOn(Rectangle mob)
    {
        int mobCenterX = mob.x + mob.width/2;
        int mobCenterY = mob.y + mob.height/2;
        Point mobCenter = new Point(mobCenterX, mobCenterY);
        Tile center = null;
        for(int row = 0; row < currentLocation.getNumRows(); row++)
        {
            for(int col = 0; col < currentLocation.getNumCols(); col++)
            {
                if(map[row][col].contains(mobCenter))
                    center = map[row][col];
            }
        }
        return center;
    }
    
    /**
     * draws the tiles and puts them all together so it looks nice
     */
    public void draw(Graphics g)
    {
        g.drawImage((Image)currentLocation.getMapImage(), 0, 0, null);
        g.drawImage(player.getImage(), player.getCoordinate().getX(), player.getCoordinate().getY(), null);
    }
    
    public BufferedImage getImage()
    {
    	BufferedImage image = new BufferedImage(GameGUI.WIDTH, GameGUI.HEIGHT, BufferedImage.TYPE_INT_ARGB);
    	Graphics g = image.getGraphics();
    	g.drawImage(currentLocation.getMapImage(), 0, 0, null);
    	g.drawImage(player.getImage(), player.getCoordinate().getX(), player.getCoordinate().getY(), null);
    	g.setColor(Color.red);
    	g.drawString(player.getCoordinate().toString(), 20, 20);
    	g.dispose();
    	return image;
    }
    private void setXDirection(int dir)
    {
    }
    
    private void setYDirection(int dir)
    {
    }
    
    /**
     * tells the camera where it should go on the map.<br>
     * Use PAN_UP, PAN_DOWN, PAN_LEFT, and PAN_RIGHT!
     */
    public void navigateMap(int nav)
    {
        switch(nav)
        {
            default: //if nav is not a direction number
                System.out.println("default, doing nothing");
                break;
            case(PAN_UP):
                setYDirection(1);
                break;
            case PAN_DOWN:
                setYDirection(-1);
                break;
            case PAN_LEFT:
                setXDirection(1);
                break;
            case PAN_RIGHT:
                setXDirection(-1);
                break;
        }
    }
    
    private void checkShouldMove()
    {
        if(map[0][0].getY() == 0)
            shouldMoveUp = false;
        else
            shouldMoveUp = true;
        if(map[currentLocation.getNumRows()-1][0].getY() == GameGUI.FRAME_HEIGHT-19)
            shouldMoveDown = false;
        else
            shouldMoveDown = true;
        if(map[0][0].getX() == 0)
            shouldMoveLeft = false;
        else
            shouldMoveLeft = true;
        if(map[0][currentLocation.getNumCols()-1].getX() == GameGUI.FRAME_WIDTH-19)
            shouldMoveRight = false;
        else
            shouldMoveRight = true;
    }
    
    public String toString()
    {
    	return "World";
    }
    
}