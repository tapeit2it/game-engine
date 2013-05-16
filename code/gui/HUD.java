package game.gui;

import game.IO;
import game.map.World;

import java.awt.Color;
import java.awt.Graphics;

public class HUD
{
	World world;
	
	public HUD(World world)
	{
		this.world = world;
	}

	public void draw(Graphics g)
	{
		g.setColor(Color.red);
    	g.drawString(world.getPlayer().getCoordinate().toString(), 20,20);
    	g.drawString(Boolean.toString(IO.key_W), 40, 40);
    	g.drawString(Boolean.toString(IO.key_A), 5, 60);
    	g.drawString(Boolean.toString(IO.key_D), 80, 60);
    	g.drawString(Boolean.toString(IO.key_S), 40, 80);
    	g.drawString(Boolean.toString(world.getPlayer().canMove()), 100, 20);
	}

}
