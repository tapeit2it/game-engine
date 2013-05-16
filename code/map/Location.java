package game.map;

import game.entity.Animal;
import game.entity.Monster;
import game.entity.NPC;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class Location
{
	private Tile[][] map;
	private TileType defaultGround;
	private int ROWS = 30, COLS = 40;
	
	private ArrayList<Animal> animals;
	private ArrayList<Monster> monsters;
	private ArrayList<NPC> residents;
	
	private Coordinate preferredSpawn = null;
	
	public Location()
	{
		animals = new ArrayList<Animal>();
		monsters = new ArrayList<Monster>();
		residents = new ArrayList<NPC>();
	}
	
	public Location(TileType defaultGround)
	{
		this();
		setDefaultGround(defaultGround);
	}
	
	public Location(int rows, int cols, TileType defaultGround)
	{
		this(rows, cols);
		setDefaultGround(defaultGround);
	}
	
	public Location(int rows, int cols)
	{
		this();
		setNumRows(rows);
		setNumCols(cols);
	}
	
	public int getNumRows()
	{
		return ROWS;
	}
	
	private void setNumRows(int rows)
	{
		ROWS = rows;
	}
	
	public int getNumCols()
	{
		return COLS;
	}
	
	private void setNumCols(int cols)
	{
		COLS = cols;
	}
    
    public void genGround()
    {
    	if(map == null) map = new Tile[ROWS][COLS];
        for(int row = 0; row < ROWS; row++)
        {
            for(int col = 0; col < COLS; col++)
            {
                addTile(new Tile(defaultGround, new Coordinate(col*Tile.WIDTH, row*Tile.HEIGHT), new Coordinate(col, row)));
            }
        }
    }
    
    public Tile[][] getMap()
    {
        return map;
    }
    
    public ArrayList<NPC> getResidents()
    {
    	return residents;
    }
    
    public ArrayList<Monster> getMonsters()
    {
    	return monsters;
    }
    
    public ArrayList<Animal> getAnimals()
    {
    	return animals;
    }

    public BufferedImage getMapImage()
    {
        BufferedImage mapImage = new BufferedImage(Tile.WIDTH * COLS, Tile.HEIGHT * ROWS, BufferedImage.TYPE_INT_ARGB);
        Graphics g = mapImage.getGraphics();
        for(int row = 0; row < map.length; row++)
            for(int col = 0; col < map[row].length; col++)
                g.drawImage(map[row][col].getType().getImage(), map[row][col].x, map[row][col].y, null);
        g.dispose();
        return mapImage;
    }
    
    public void addBuilding(Building build, int x, int y)
    {
        String[] floorplan = build.loadFloorPlan();
        for(int row = 0; row < floorplan.length; row++)
        {
            char[] rowChars = floorplan[row].toCharArray();
            for(int col = 0; col < rowChars.length; col++)
            {
                if(build.getType(rowChars[col]) != null)
                addTile(new Tile(build.getType(rowChars[col]), new Coordinate((x+col)*Tile.WIDTH, (y+row)*Tile.HEIGHT), new Coordinate(x+col, y+row)));
                else
                addTile(new Tile(defaultGround, new Coordinate((x+col)*Tile.WIDTH, (y+row)*Tile.HEIGHT), new Coordinate(x+col, y+row)));
            }
        }
    }
    
    public TileType getDefaultGround()
    {
    	return defaultGround;
    }
    
    public void setDefaultGround(TileType type)
    {
    	defaultGround = type;
    }
    
    /**
     * adds a new Tile to the world at given coordinates
     * if already a Tile there, it will replace but print message saying that
     * @param tile what to add
     */
    protected void addTile(Tile tile)
    {
        //if a space exists for the tile
        //if(map[tile.getMapRow()][tile.getMapColumn()]!=null)System.out.println("Replacing Tile at " +tile.getMapRow()+ ", " +tile.getMapColumn()+"!");
        this.map[tile.getMapRow()][tile.getMapColumn()] = tile;
    }
    
    /**
     * replaces the current tile with newTile at given coords
     * @param tile - what to replace old Tile with
     */
    @SuppressWarnings("unused")
	private void removeTile(Tile tile)
    {
        map[tile.getMapRow()][tile.getMapColumn()] = null;
    }
    
    public Coordinate getPreferredSpawn()
    {
    	return preferredSpawn;
    }
    
    @SuppressWarnings("unused")
	private void setPreferredSpawn(Coordinate spawn)
    {
    	preferredSpawn = spawn;
    }

}
