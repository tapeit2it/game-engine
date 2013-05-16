package game.map;


public class Coordinate
{
    private int x,y;
    
    public Coordinate(int xCoord, int yCoord)
    {
        x = xCoord;
        y = yCoord;
    }
    
    public void setX(int Coord)
    {
        x = Coord;
    }
    
    public void setY(int Coord)
    {
        y = Coord;
    }
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public void changeY(int amount)
    {
    	y+=amount;
    }
    
    public void changeX(int amount)
    {
    	x+=amount;
    }
    
    public String toString()
    {
    	return "(" + x + ", " + y + ")";
    }
}
