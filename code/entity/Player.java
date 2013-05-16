package game.entity;

import game.IO;
import game.entity.item.Item;
import java.util.*;
import java.awt.*;

public class Player extends Living
{
    private Item[] Inventory;
    private final Item[] emptyInv = new Item[0];
    /**
     * makes a new Player
     */
    public Player()
    {
    }
    
    
    /**
     * @return current Inventory of the Player
     */
    public Item[] getInventory()
    {
        return Inventory;
    }
    
    /**
     * adds an Item to the Player's current Inventory
     */
    public void addItem(Item item)
    {
        int len = Inventory.length;
        Inventory[len] = item;
    }
    
    public void removeItem(Item item)
    {
        ArrayList<Item> tempInv = new ArrayList<Item>(Arrays.asList(Inventory));
        tempInv.remove(item);
        Inventory = tempInv.toArray(emptyInv);
    }
    
    public void attack()
    {
        
    }
    
    public void draw(Graphics g)
    {
        if(visible)
        {
            g.drawImage(getImage(), mapCoordinate.getX(), mapCoordinate.getY(), null);
            g.setColor(Color.red);
            g.drawString(mapCoordinate.toString(), 20,20);
        }
    }
    
	@Override
	public void calculateMove() 
	{
		int x = 0, y = 0;
		if(IO.key_W)y++;
		if(IO.key_S)y--;
		if(IO.key_D)x++;
		if(IO.key_A)x--;
		
		if(y > 0)
		{
			if(x != 0)
			{
				if(x > 0)
				{
					setDirection(Living.NORTHEAST);
				}
				else
				{
					setDirection(Living.NORTHWEST);
				}
			}
			else
			{
				setDirection(Living.NORTH);
			}
		}
		else if(y < 0)
		{
			if(x != 0)
			{
				if(x > 0)
				{
					setDirection(Living.SOUTHEAST);
				}
				else
				{
					setDirection(Living.SOUTHWEST);
				}
			}
			else
			{
				setDirection(Living.SOUTH);
			}
		}
		else
		{
			if(x > 0)
			{
				setDirection(Living.EAST);
			}
			else if(x < 0)
			{
				setDirection(Living.WEST);
			}
		}
	}
	
	public String toString()
	{
		return "Player in " + world;
	}
}