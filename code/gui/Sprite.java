package game.gui;

import game.IO;
import game.entity.*;
import game.entity.item.Item;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Sprite 
{
	private Rectangle bounds;
	
	private int scaleX = 1;
	private int scaleY = 1;
	
	/**
	 * how many frames are in the animation
	 */
	private int frameCount;
	/**
	 * which frame the animation is on
	 */
	private int loopCount = 0;
	private BufferedImage currentFrame;
	private ArrayList<BufferedImage> frames = new ArrayList<BufferedImage>();
	
	private String imagePath = "/images/";
	
	private int state = 0;

	private boolean animated;
	
	public Sprite(String imagePath)
	{
		this.imagePath = imagePath;
		animated = false;
		loadFrames();
	}
	public Sprite(Living entity)
	{
		imagePath += "mobs/" + entity.getClass().getSimpleName().toLowerCase() + "/";
		animated = false;
		loadFrames();
	}
	
	public Sprite(Living entity, int frameCount)
	{
		imagePath += "mobs/" + entity.getClass().getSimpleName().toLowerCase() + "/";
		setFrameCount(frameCount);
		animated = true;
		loadFrames();
	}
	
	public Sprite(Item item)
	{
		imagePath += "items/";
		animated = false;
		loadFrames();
	}

	public Rectangle getBounds() 
	{
		return bounds;
	}

	public void setBounds(Rectangle bounds) 
	{
		this.bounds = bounds;
	}
	
	public void setState(int state)
	{
		this.state = state;
		updateFrame();
	}
	
	public void scale(int scale)
	{
		scaleX = scale;
		scaleY = scale;
	}
	
	public void scale(int scaleX, int scaleY)
	{
		this.scaleX = scaleX;
		this.scaleY = scaleY;
	}
	
	public void updateFrame()
	{
		switch(state)
		{
		case Living.MOVING_UP:
			imagePath += "up.png";
			break;
		case Living.MOVING_DOWN:
			imagePath += "down.png";
			break;
		case Living.MOVING_LEFT:
			imagePath += "left.png";
			break;
		case Living.MOVING_RIGHT:
			imagePath += "right.png";
			break;
		}
		if(animated)
		{
			if(loopCount == frameCount)loopCount = 0;
			currentFrame = frames.get(loopCount);
			loopCount++;
		}
		else currentFrame = frames.get(0);
	}
	public BufferedImage getImage()
	{
		return currentFrame;
	}

	public int getFrameCount() 
	{
		return frameCount;
	}

	public void setFrameCount(int frameCount) 
	{
		this.frameCount = frameCount;
	}
	
	/**
	 * <b>NOTE:</b> this assumes that each frame in the sprite sheet is the same size and frames are arranged vertically
	 * <p>
	 * if you want it to consider different sizes, find a different game engine
	 */
	public void loadFrames()
	{
		if(animated)
		{
			BufferedImage spriteSheet = IO.readImage(imagePath);
			int sheetHeight = spriteSheet.getHeight();
			int frameHeight = sheetHeight/frameCount;
			int frameWidth = spriteSheet.getWidth();
			for(int i = 0; i < frameCount; i++)
			{
				frames.add(spriteSheet.getSubimage(0, frameHeight*i, frameWidth, frameHeight));
			}
		}
		else frames.add(IO.readImage(imagePath));
	}
	
	public void startAnimation()
	{
		animated = true;
		loopCount = 0;
	}
	
	public void stopAnimation()
	{
		animated = false;
		loopCount = 0;
	}
	
	public boolean isAnimated()
	{
		return animated;
	}
	
	public void setImagePath(String imagePath) 
	{
		this.imagePath = imagePath;
		loadFrames();
	}
}
