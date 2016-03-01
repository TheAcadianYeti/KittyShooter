package Enemies;
import General.*;
public class ScratchPost extends Sprite
{
	private static final int LENGTH = 100, WIDTH = 100, DELAY = 40, ANGLE = 0;
	private final String LOAD_FILE = "Sprites/ScratchPost/load_file.txt";
	public ScratchPost(int xPos, int yPos)
	{
		super(WIDTH, LENGTH, xPos, yPos, ANGLE, DELAY);
	}
	@Override
	public void update() 
	{
		
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean loadFrames() 
	{
		// TODO Auto-generated method stub
		frames = MainController.loadFrames(LOAD_FILE);
		return false;
	}

}
