package MainCharacter;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import General.MainController;
/**
 * Models the default bullet object, shouldoverrite the draw method if need be
 * @author Patty
 *
 */
public class DefaultBullet extends Bullet
{
	private final String LOAD_FILE = "Sprites/Bullets/DefaultBullet/default_bullet_load.txt";
	public static final int DELAY = 5;
	private static final int DAMAGE = 5, SPEED = 7, DURATION = 3, WIDTH = 10, HEIGHT = 10, FRAME_DELAY = 5, OFFSET = 40;
	public DefaultBullet(int xPos, int yPos, double angle)
	{
		//Duration represents how many seconds we have
		//Need to find relativve xPos and yPos
		super(DAMAGE, SPEED, DURATION * DELAY_MULTIPLIER, WIDTH, HEIGHT, xPos + (int)(OFFSET * Math.cos(angle)), yPos + (int)(OFFSET * Math.sin(angle)), angle, FRAME_DELAY);
	}
	
	
	
	
	/**
	 * Overriting update method
	 */
	public void update()
	{
		//Convert from angle to new number
		//Use speed and xpos, ypos
		//Find xSpeed
		xPos += Math.cos(angle) * SPEED;
		yPos += Math.sin(angle) * SPEED;
		//Remember this duration represents the life remaining
		duration--;
	}
	
	
	/**
	 * Overriting draw method, default bullet just updates the bullet location
	 */
	public void draw(Graphics g)
	{

		super.draw(g);
	}
	
	/**
	 * Method to load our frames in for our bullet, only two frames here
	 */
	public boolean loadFrames()
	{
		//The frames located in this bullet are in the bullet/generic folder
		//Use the helper method in MainController to do this
		frames = MainController.loadFrames(LOAD_FILE);
		if(frames == null)
		{
			//Failed to get images
			System.exit(1);
			System.err.println("Failed to read default bullet images");
			
		}
		return true;
	}

}
