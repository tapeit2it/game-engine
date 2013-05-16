package game.map;

import java.awt.Rectangle;

public class Tile extends Rectangle
{
	private static final long serialVersionUID = 1L;
	public static final byte WIDTH = 20, HEIGHT = 20;
    private Coordinate mapCoordinate;
    TileType type;
    public Tile(TileType tile, Coordinate worldCoord, Coordinate mapCoord)
    {
        super();
        super.x = worldCoord.getX();
        super.y = worldCoord.getY();
        super.height = HEIGHT;
        super.width = WIDTH;
        type = tile;
        mapCoordinate = mapCoord;
    }
    public Rectangle rect()
    {
        return this;
    }
    public TileType getType()
    {
        return type;
    }
    
    public int getMapRow()
    {
        return mapCoordinate.getY();
    }
    
    public int getMapColumn()
    {
        return mapCoordinate.getX();
    }
    
    public String toString()
    {
    	return getType() + " tile at " + mapCoordinate;
    }
}