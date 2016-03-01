package MainCharacter;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import General.MainController;

public class SpreadBullet extends Bullet
{
	private static final String LOAD_FILE = "Sprites/Bullets/SpreadBullet/spread_bullet_load.txt";
	//ammo count represents how many bullets you have for this particular type of ammo
	public static final int DELAY = 10, AMMO_COUNT = 50;
	private static final int DAMAGE = 5, SPEED = 10, DURATION = 1, WIDTH = 40, HEIGHT = 20, FRAME_DELAY = 10, OFFSET = 50;
	public SpreadBullet(int xPos, int yPos,double  angle)
	{
		super(DAMAGE, SPEED, DURATION * DELAY_MULTIPLIER, WIDTH, HEIGHT, xPos + (int)(OFFSET * Math.cos(angle)), yPos + (int)(OFFSET * Math.sin(angle)), angle, FRAME_DELAY);
		
	}

	
	/**
	 * Overriting update method
	 */
	public void update()
	{
		xPos += Math.cos(angle) * SPEED;
		yPos += Math.sin(angle) * SPEED;
		//Remember this duration represents the life remaining
		duration--;
	}
	
	
	/**
	 * Overriting draw method, nothing special here
	 */
	public void draw(Graphics g)
	{

		super.draw(g);
	}
	

	
	@Override
	public boolean loadFrames() 
	{
		frames = MainController.loadFrames(LOAD_FILE);
		return true;
	}
}
