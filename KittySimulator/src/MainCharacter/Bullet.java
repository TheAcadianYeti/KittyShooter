package MainCharacter;
import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

import General.*;
/**
 * Represents the super class to be extended by all types of bullets, each bullet has a duration, speed, damage and should know it's inital angle of firing
 * and that is all for now
 * @author Patty
 *
 */
public abstract class Bullet extends Sprite
{
	//Duration represents the amount of in game seconds, represented by frame calls that the bullet stays on screen for
	protected int damage, speed, duration, width, height;
	protected static final int DELAY_MULTIPLIER = 40;
	public Bullet(int damage, int speed, int duration, int width, int height, int xPos, int yPos , double angle, int delay)
	{
		super(width, height, xPos, yPos, angle, delay);
		this.damage = damage;
		this.speed = speed;
		this.duration = duration;
	}
	
	/**
	 * The super class will implement the basic draw, which should be as simple as calling the super draw
	 */
	public void draw(Graphics g)
	{
		//Go ahead and draw based on angle and so on
		
		super.draw(g);
	}

	public int getDuration()
	{
		return duration;
	}
	public void setDuration(int duration)
	{
		this.duration = duration;
	}
	
	
	/**
	 * Frame getter
	 * 
	 */
	public BufferedImage[] getFrames()
	{
		return this.frames;
	}
	/**
	 * Set frames, sets the frames
	 */
	public void setFrames(BufferedImage[] frames)
	{
		this.frames = frames;
	}
	
	
	public abstract boolean loadFrames();
}
