package game.map.buildings;

import game.map.Building;
import game.map.TileType;

public class GenericHouse extends Building
{
    public GenericHouse()
    {
        floor = TileType.WOODEN_FLOOR;
        wall = TileType.STONE_BRICK;
        floorPlan = new String[5];
        floorPlan[0] = "#########";
        floorPlan[1] = "#0000000#";
        floorPlan[2] = "#0000000#";
        floorPlan[3] = "#0000000#";
        floorPlan[4] = "######0##";
    }
    public TileType getType(char symbol)
    {
        switch(symbol)
        {
            case '#':
            return wall;
            case '0':
            return floor;
            case ' ':
            return null;
        }
        return null;
    }
}
