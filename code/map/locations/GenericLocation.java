package game.map.locations;

import game.map.Location;
import game.map.TileType;
import game.map.buildings.GenericHouse;

public class GenericLocation extends Location
{
    public GenericLocation()
    {
    	super(40,40);
        setDefaultGround(TileType.GRASS);
        genGround();
        addBuilding(new GenericHouse(), 7, 5); 
    }
}
