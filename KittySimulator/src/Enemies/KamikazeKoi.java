package Enemies;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import General.MainController;
import General.Sprite;

public class KamikazeKoi extends Sprite
{
	private static final int WIDTH = 20, HEIGHT = 20, ANGLE_OFFSET = 5, DELAY = 5;
	private final int SPEED = 5;
	private final String LOAD_FILE = "Sprites/Koi/koi_load.txt";
	private Sprite target;//Represents who the koi is attacking
	
	
	//Testing purposes
	private ArrayList<DeathShard> testArray = new ArrayList<DeathShard>();
	public KamikazeKoi(int xPos, int yPos, double angle, Sprite target)
	{
		super(WIDTH, HEIGHT, xPos, yPos, angle + ANGLE_OFFSET, DELAY);
		this.target = target;

	}
	
	/**
	 * Overriting the update method
	 */
	public void update()
	{
		//Need to genereate an x and y speed and such, and update
		//Just point towards and move towards the main character
		int targetX = target.getX(), targetY = target.getY();
		//Let's get the dx/dy in terms of difference in position
		targetX = targetX - xPos;
		targetY = targetY - yPos;
		//Use tan to get our angle
		
		setAngle(Math.atan2((double)targetY, (double)targetX));
		//Move relative to that
		int dx, dy;
		dx = (int)(SPEED * Math.cos(angle));
		dy = (int)(SPEED*Math.sin(angle));
		super.update(dx,  dy);
		
	}
	
	
	@Override
	public boolean loadFrames() 
	{
		
		// TODO Auto-generated method stub
		frames= MainController.loadFrames(LOAD_FILE);
		return false;
	}
	
	/**
	 * Just testing a custom death animation thing, shatter effect
	 */
	public void setupDeath()
	{
		//Take the first frame for now, just need a buffered image
		BufferedImage original = frames[0];
		int xPos = 0, yPos = 0;
		while(xPos <= original.getWidth())
		{
			
		}
		
	}
	
	/**
	 * Died function, just for testing for now
	 */
	public void died()
	{
		
	}
	
	/**
	 *
	 */
	private class DeathShard extends Sprite
	{
		BufferedImage image;
		public DeathShard(BufferedImage image, int width, int height, int xPos, int yPos,int duration)
		{
			super(width, height, xPos, yPos, 0.0, 1);
		}
		@Override
		public void update() 
		{
			// TODO Auto-generated method stub
			
		}
		@Override
		public boolean loadFrames() {
			// TODO Auto-generated method stub
			return false;
		}
	}
}
