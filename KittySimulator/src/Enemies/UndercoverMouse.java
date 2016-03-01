package Enemies;

import General.MainController;
import General.Sprite;
public class UndercoverMouse extends Sprite
{
	private final String load_file = "Sprites/UndercoverMouse/load_file.txt";
	private static final int SPEED = 5, WIDTH = 20, HEIGHT = 20, ANGLE = 0, DELAY = 1;
	private final double TURN_SPEED = 0.4;//How many degrees a frame do we turn
	private int updateCount = 0;
	public UndercoverMouse(int xPos, int yPos)
	{
		super(WIDTH, HEIGHT, xPos, yPos, ANGLE, DELAY);
	}
	
	/**
	 * Overriting the update method, simply rotate our angle by the turn speed
	 */
	public void update()
	{
		if(updateCount++ > DELAY)
		{
			setAngle(angle += TURN_SPEED);
			updateCount = 0;
		}
		
	}

	@Override
	public boolean loadFrames() 
	{
		// TODO Auto-generated method stub
		frames = MainController.loadFrames(load_file);
		return false;
	}
	

}
