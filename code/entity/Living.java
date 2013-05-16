package game.entity;

import game.IO;
import game.gui.Sprite;
import game.map.Coordinate;
import game.map.Tile;
import game.map.World;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

public abstract class Living
{
    protected String Name;
    protected int maxHealth;
    protected int currentHealth;
    protected int HEIGHT = 18,WIDTH = 18;
    protected World world;
    /**
     * cardinal directions
     */
    public static final int NORTH = 0,
    						NORTHEAST = 45,
    						EAST = 90,
    						SOUTHEAST = 135,
    						SOUTH = 180,
    						SOUTHWEST = 225,
    						WEST = 270,
    						NORTHWEST = 315;
    protected int  xDirection, yDirection;
    protected Coordinate mapCoordinate;
    @SuppressWarnings("unused")
	private boolean interactable;
    protected boolean visible = true;
    private boolean canMoveLeft = true, canMoveRight = true,
                    canMoveUp = true, canMoveDown = true;
    private int direction;
    private Sprite sprite;
    public static final int MOVING_LEFT = 0,
    						MOVING_RIGHT = 1,
    						MOVING_UP = 2,
    						MOVING_DOWN = 3,
    						SITTING = 4,
    						LIEING_DOWN = 5,
    						SWIMMING_LEFT = 6,
    						SWIMMING_RIGHT = 7,
    						SWIMMING_UP = 8,
    						SWIMMING_DOWN = 9;
    private int currentState = 0;
    
    public Living()
    {
    	sprite = new Sprite(this);
    }
    /**
     * spawns a new creature into the given world at given coordinates
     * @param world the world that the creature shall be in
     * @param coord the coordinates to originally spawn the creature at in the current Location
     */
    public void spawn(World world, Coordinate coord)
    {
        this.world = world;
        mapCoordinate = coord;
        setCurrentState(0);
    }
    
    public Image getImage()
    {
    	return IO.readImage("/images/mob/Player/up.png");
//        return sprite.getImage();
    }
    
    public Sprite getSprite()
    {
    	return sprite;
    }
    /**
     * damages the creature a given amount
     * @param amount the amount of health to take from the creature
     */
    public void damage(int amount)
    {
        if(currentHealth > amount)
            currentHealth -= amount;
        else
        {
            currentHealth = 0;
            die();
        }
    }
    
    /**
     * heals the creature a given amount
     * @param amount the amount of health to give to give to the creature
     */
    public void heal(int amount)
    {
        if(currentHealth+amount<=maxHealth)
            currentHealth += amount;
        else
            currentHealth = maxHealth;
    }
    
    /**
     * @return the current health of the creature
     */
    public int getHealth()
    {
        return currentHealth;
    }
    
    /**
     * updates the creature
     */
    public void update()
    {
    	calculateMove();
        if(IO.key_A || IO.key_D || IO.key_S || IO.key_W) 
		{
        	move();
    	}
        switch(getDirection())
    	{
    	case NORTH:
    		setCurrentState(MOVING_UP);
    		break;
    	case SOUTH:
    		setCurrentState(MOVING_DOWN);
    		break;
    	case NORTHEAST:
    	case EAST:
    	case SOUTHEAST:
    		setCurrentState(MOVING_RIGHT);
    		break;
    	case NORTHWEST:
    	case WEST:
    	case SOUTHWEST:
    		setCurrentState(MOVING_LEFT);
    		break;
    	}
    }
    
    /**
     * changes the visibility of the creature
     * @param vis whether the creature is visible or not
     */
    public void setVisible(boolean vis)
    {
        visible = vis;
    }
    
    /**
     * @return the current coordinate of the creature
     */
    public Coordinate getCoordinate()
    {
    	return mapCoordinate;
    }
    
    /**
     * moves the creature forward in whichever direction he is facing
     * 
     */
    public void move()
    {
    	if(canMove())
	    	switch(direction)
	    	{
		    	case NORTH:
		    		mapCoordinate.changeY(-1);
		    		break;
		    	case NORTHEAST:
		    		mapCoordinate.changeY(-1);
		    		mapCoordinate.changeX(1);
		    		break;
		    	case EAST:
		    		mapCoordinate.changeX(1);
		    		break;
		    	case SOUTHEAST:
		    		mapCoordinate.changeY(1);
		    		mapCoordinate.changeX(1);
		    		break;
		    	case SOUTH:
		    		mapCoordinate.changeY(1);
		    		break;
		    	case SOUTHWEST:
		    		mapCoordinate.changeY(1);
		    		mapCoordinate.changeX(-1);
		    		break;
		    	case WEST:
		    		mapCoordinate.changeX(-1);
		    		break;
		    	case NORTHWEST:
		    		mapCoordinate.changeY(-1);
		    		mapCoordinate.changeX(-1);
		    		break;
	    	}
    }

    /**
     * @return whether the creature is able to move in the direction it is facing
     */
    public boolean canMove()
    {
    	checkforCollision();
    	canMoveUp &= mapCoordinate.getY() >= 0;
		canMoveRight &= mapCoordinate.getX() <= (world.getCurrentLocation().getNumCols()*Tile.WIDTH)-WIDTH;
		canMoveDown &= mapCoordinate.getY() <= (world.getCurrentLocation().getNumRows()*Tile.HEIGHT)-HEIGHT;
		canMoveLeft &= mapCoordinate.getX() >= 0;
    	switch(direction%360)
    	{
    	case NORTH:
    		return canMoveUp;
    	case NORTHEAST:
    		boolean canMoveUpRight = canMoveRight && canMoveUp;
    		if(canMoveUpRight) return true;
    		if(!(canMoveUp || canMoveRight)) return false;
    		else if(canMoveUp)setDirection(NORTH);
    		else if(canMoveRight)setDirection(EAST);
    		return canMove();
    	case EAST:
    		return canMoveRight;
    	case SOUTHEAST:
    		boolean canMoveDownRight = canMoveRight && canMoveDown;
    		if(canMoveDownRight) return true;
    		if(!(canMoveDown || canMoveRight)) return false;
    		else if(canMoveDown)setDirection(SOUTH);
    		else if(canMoveRight)setDirection(EAST);
    		return canMove();
    	case SOUTH:
			return canMoveDown;
    	case SOUTHWEST:
    		boolean canMoveDownLeft = canMoveLeft && canMoveDown;
    		if(canMoveDownLeft) return true;
    		if(!(canMoveDown || canMoveLeft)) return false;
    		else if(canMoveDown)setDirection(SOUTH);
    		else if(canMoveLeft)setDirection(WEST);
    		return canMove();
    	case WEST:
    		return canMoveLeft;
    	case NORTHWEST:
    		boolean canMoveUpLeft = canMoveLeft && canMoveUp;
    		if(canMoveUpLeft) return true;
    		if(!(canMoveUp || canMoveLeft)) return false;
    		else if(canMoveUp)setDirection(NORTH);
    		else if(canMoveLeft)setDirection(WEST);
    		return canMove();
    	}
		return false;
    }
    
    /**
     * @return the direction in degrees that the creature is facing
     */
    public int getDirection() 
    {
		return direction;
	}
    
    /**
     * sets the creature direction to a specified amount
     * @param direction the degree to face
     */
	public void setDirection(int direction) 
	{
		this.direction = direction;
	}
    
	public int getCurrentState() 
	{
		return currentState;
	}

	public void setCurrentState(int currentState) 
	{
		this.currentState = currentState;
		sprite.setState(currentState);
	}

	/**
     * checks to see if the creature is currently colliding with a solid tile
     * @TODO does not check for collisions diagonally
     */
    public void checkforCollision()
    {
    	canMoveUp = true;
    	canMoveDown = true;
    	canMoveLeft = true;
    	canMoveRight = true;
        Rectangle r = getBounds();
        ArrayList<Tile> collided = world.getOccupiedSolidTiles(r);
        Tile centerTile = world.getTileCenteredOn(r);
        
        /**
         * finds out which direction to stop in
         */
        if(!collided.isEmpty())
        {
            for(Tile tile : collided)
            {
                int mapRow = tile.getMapRow();
                int mapCol = tile.getMapColumn();
                boolean upCollision = centerTile.getMapRow() > mapRow && mapCol == centerTile.getMapColumn(),
                        downCollision = centerTile.getMapRow() < mapRow && mapCol == centerTile.getMapColumn(),
                        leftCollision = centerTile.getMapColumn() > mapCol && mapRow == centerTile.getMapRow(),
                        rightCollision = centerTile.getMapColumn() < mapCol && mapRow == centerTile.getMapRow();
                if(downCollision)
                    canMoveDown = false;
                if(upCollision)
                    canMoveUp = false;
                if(rightCollision)
                    canMoveRight = false;
                if(leftCollision)
                    canMoveLeft = false;
                
            }
        }
    }
    
    /**
     * kills the creature
     */
    public void die()
    {
        setVisible(false);
    }
    
    /**
     * @return the collision box for the creature
     */
    public Rectangle getBounds()
    {
        return new Rectangle(mapCoordinate.getX(), (int) (mapCoordinate.getY()+(HEIGHT*.3)), WIDTH, (int) (HEIGHT*.7));
    }
    public abstract void attack();
    public abstract void draw(Graphics g);

	public abstract void calculateMove();
	    
}