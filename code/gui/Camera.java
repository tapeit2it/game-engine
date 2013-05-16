package game.gui;

import java.awt.Graphics;
import java.awt.Image;

import game.map.World;

public class Camera
{
    public static final int PAN_UP = 0, 
                            PAN_DOWN = 1, 
                            PAN_LEFT = 2,
                            PAN_RIGHT = 3;
    private World world;
    public Camera(World subject)
    {
        world = subject;
    }
    public void pan(int dir)
    {
        switch(dir)
        {
            case(PAN_UP):
            panUp();
            break;
            case(PAN_DOWN):
            panDown();
            break;
            case(PAN_LEFT):
            panLeft();
            break;
            case(PAN_RIGHT):
            panRight();
            break;
        }
    }
    
    public void panUp()
    {
        
    }
    
    public void panDown()
    {
        
    }
    
    public void panLeft()
    {
        
    }
    
    public void panRight()
    {
        
    }
    
	public void draw(Graphics g) 
	{
		g.drawImage((Image)world.getImage(), 0,0,null);
	}
    

//    /**
//     * moves the camera to where it should go
//     */
//    public void moveMap()
//    {
//        boolean yPositive = (world.yDirection > 0);
//        boolean yNegative = (yDirection < 0);
//        boolean xPositive = (xDirection > 0);
//        boolean xNegative = (xDirection < 0);
//        for(int row = 0; row < currentLoc.ROWS; row++)
//        {
//            for(int col = 0; col < currentLoc.COLS; col++)
//            {
//                if(shouldMoveLeft||shouldMoveRight)
//                {
//                    if(xPositive && shouldMoveLeft)
//                        map[row][col].x += xDirection;
//                    if(xNegative && shouldMoveRight)
//                        map[row][col].x += xDirection;
//                }
//                if(shouldMoveUp || shouldMoveDown)
//                {
//                    if(yPositive && shouldMoveUp)
//                        map[row][col].y += yDirection;
//                    if(yNegative && shouldMoveDown)
//                        map[row][col].y += yDirection;
//                }
//            }
//        }
//    }
//    
//    /**
//     * called when camera reaches destination it needs to go
//     */
//    public void stopMoveMap()
//    {
//        setXDirection(0);
//        setYDirection(0);
//    }
    
}
