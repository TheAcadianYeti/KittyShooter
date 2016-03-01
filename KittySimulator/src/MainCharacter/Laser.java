package MainCharacter;
import java.awt.*;
import java.awt.geom.AffineTransform;

import javax.swing.*;

import General.MainController;
public class Laser extends Bullet
{
	private static final String LOAD_FILE = "Sprites/Bullets/LaserBullet/laser_load.txt";
	public static final int DELAY = 1, AMMO_COUNT = 500;
	
	private static final int OFFSET = 240;//Must be two times length?, plus another 20 for effect
	private static final int DAMAGE = 5, SPEED = 7, DURATION = 2, WIDTH = 100, HEIGHT = 20, FRAME_DELAY = 5;
	public Laser(int xPos, int yPos, double angle)
	{
		//Calculating offset in the constructor
		super(DAMAGE, SPEED, DURATION, WIDTH, HEIGHT, xPos + (int)(OFFSET* Math.cos(angle)), yPos + (int)(OFFSET * Math.sin(angle)), angle, FRAME_DELAY);
	}
	
	
	
	/**
	 * Updates the laser position based on movement
	 * @param xPos
	 * @param yPos
	 * @param angle
	 */
	public void update(int xPos, int yPos, double angle)
	{
		//Udpate data based on current position, also maybe we should reset duration here....
		duration = DURATION;
		this.angle = angle;
		this.xPos = xPos + (int)(OFFSET * Math.cos(angle));
		this.yPos = yPos + (int)(OFFSET * Math.sin(angle));
		
		
	}
	
	/**
	 * Overriting the loadFrames method
	 */
	public boolean loadFrames()
	{
		frames = MainController.loadFrames(LOAD_FILE);
		return true;
	}
	
	/**
	 * Overriting update method
	 */
	public void update()
	{
		duration--;
	}
	
	/**
	 * Nothign special about draw
	 */
	public void draw(Graphics g)
	{
		
		super.draw(g);
	}
	
}
