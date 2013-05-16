package game.map.locations;

import game.map.*;
import game.map.buildings.*;

public class GenericLocation2 extends Location
{
    public GenericLocation2()
    {
        super(40,40);
        setDefaultGround(TileType.STONE);
        genGround();
        addBuilding(new GenericHouse(), 10, 10); 
    }
}
