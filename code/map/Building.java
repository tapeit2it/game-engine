package game.map;

public abstract class Building
{
    protected TileType wall, floor;
    protected String[] floorPlan;
    public String[] loadFloorPlan()
    {
        return floorPlan;
    }
    public abstract TileType getType(char symbol);
}