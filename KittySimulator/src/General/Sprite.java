package General;
import java.util.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

import javax.swing.*;

import java.awt.image.BufferedImage;

public abstract class Sprite
{
	//X and y are based on top left corner of sprite
	private double width, height;
	protected int xPos;
	protected int yPos;
	//Angle is in radians
	protected double angle;
	private int delay;
	private int worldWidth = -1, worldHeight = -1;//Represents the height and width of the world this sprite exists in, the -1 indicates a height and width we don't careabout
	//Determines if we reverse the animation after stepping through
	protected boolean reverseAnimation = false, reversing = false;
	private int currentDelay, currentFrame;
	protected BufferedImage[] frames;
	
	public Sprite(int width, int height, int xPos, int yPos,  double angle, int delay)
	{
		this.angle = angle;
		this.delay = delay;
		currentDelay = 0;
		currentFrame = 0;
		this.width = width;
		this.height = height;
		this.xPos = xPos;
		this.yPos = yPos;
		
	}
	
	

	
	
	
	/**
	 * Abstrat update method. to be defined by all sprites
	 */
	public abstract void update();
	/**
	 * The class that extends Sprite will use this method to update it's position based on it's speed and movement
	 * @param dx
	 * @param dy
	 */
	public void update(int dx, int dy)
	{
		if(worldWidth == -1)
		{
			xPos += dx;
		}
		else
		{
			int testX = xPos + dx;
			//20 because of innate offset with walls and such
			if(!(testX >= worldWidth - width) && testX >= 20)
			{
				xPos = testX;
			}
		}
		
		//Checking height as well
		if(worldWidth == -1)
		{
			yPos += dy;
		}
		else
		{
			int testY = yPos + dy;
			if(!(testY >= worldHeight - height) && testY >= 20)
			{
				yPos = testY;
			}
		}
	}
	
	/**
	 * Draws at a different x and y, will be used by the camera
	 */
	public void drawAt(Graphics g, int xPos, int yPos)
	{
		//Need to also do rotation now
		AffineTransform hrm = new AffineTransform();
		//Translate to current sprite position
		hrm.translate(xPos, yPos);
		//Rotate based off of our rotation
		//Note the rotation occurs based off of radians
		hrm.rotate(angle);
		//Also the scale,note that all sprites will be assumed to be....50/ 50
		hrm.scale(width/50, height/50);
		
		//Rotating around center of image by doing this translation
		hrm.translate(-frames[currentFrame].getWidth()/2, -frames[currentFrame].getHeight()/2);
		
		Graphics2D g2 = (Graphics2D) g;
		
		g2.drawImage(frames[currentFrame], hrm, null );
		frameStep();
	
	}
	
	/**
	 * Will draw the sprite with the current frame of animation
	 */
	public void draw(Graphics g)
	{
		//Need to also do rotation now
		AffineTransform hrm = new AffineTransform();
		//Translate to current sprite position
		hrm.translate(xPos, yPos);
		//Rotate based off of our rotation
		//Note the rotation occurs based off of radians
		hrm.rotate(angle);
		//Also the scale,note that all sprites will be assumed to be....50/ 50
		hrm.scale(width/50, height/50);
		
		//Rotating around center of image by doing this translation
		hrm.translate(-frames[currentFrame].getWidth()/2, -frames[currentFrame].getHeight()/2);
		
		Graphics2D g2 = (Graphics2D) g;
		
		g2.drawImage(frames[currentFrame], hrm, null );
		frameStep();
		
	}
	
	
	/**
	 * Helper method to step through to the next frame
	 */
	private void frameStep()
	{
		if(reverseAnimation)
		{
			currentDelay++;
			if(currentDelay > delay)
			{
				currentDelay = 0;
				if(reversing)
				{
					currentFrame--;
					if(currentFrame < 0)
					{
						currentFrame = 0;
						reversing = false;
					}
				}
				else
				{
					currentFrame++;
					if(currentFrame >= frames.length)
					{
						currentFrame = frames.length-1;
						reversing = true;
					}
				}
			}
			
		}
		else
		{
			currentDelay++;
			if(currentDelay > delay)
			{
				
				currentDelay = 0;
				currentFrame++;
				
				//If we are, then we must reset to frame 1
				if(currentFrame >= frames.length)
				{
					currentFrame = 0;
				}
			}
		}
	}
	
	
	/**
	 * Getters and setters for angles
	 */
	public void setAngle(double angle){this.angle = angle;}
	public double getAngle(){return this.angle;}
	/*
	 * Getters and setters for x and y Pos
	 */
	public void setX(int xPos){this.xPos = xPos;}
	public int getX(){return xPos;}
	
	public void setY(int yPos){this.yPos = yPos;}
	public int getY(){return yPos;}
	
	/**
	 * GEtters and setters for world height/width
	 */
	public void setWorldWidth(int width){this.worldWidth = width;}
	public void setWorldHeight(int height){this.worldHeight = height;}
	
	/**
	 * Getters and setters for frames
	 */
	public void setFrames(BufferedImage[] frames){this.frames = frames;}
	public BufferedImage[] getFrames(){return frames;}
	
	
	/**
	 * Load method that must be declared by anything extending this to load all frames in
	 * @author Patty
	 *
	 */
	public abstract boolean loadFrames();
	
	private enum status
	{
		INVINCIBLE, ARMOUR;
	}
	
	
}
