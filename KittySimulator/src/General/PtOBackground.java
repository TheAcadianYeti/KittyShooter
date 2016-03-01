package General;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import java.io.*;
public class PtOBackground extends Sprite
{
	
	private static int WIDTH = 200, HEIGHT = 400 , XPOS = 0, YPOS = 0, DELAY = 1, ANGLE = 0;
	private String LOAD_FILE = "Sprites/UI/Backgrounds/TEST_BACKGROUND.jpg";
	private Camera camera;
	public PtOBackground(Camera camera)
	{
		
		super(WIDTH, HEIGHT, XPOS, YPOS, (double)ANGLE, DELAY);
		this.camera = camera;
		loadFrames();
	}
	
	
	/**
	 * Overriding update method
	 */
	public void update()
	{
		//We just need to update our x and y based off of the cameraposition
		xPos = XPOS- camera.getX();
		yPos = YPOS - camera.getY();
	}
	
	
	/**
	 * Overriding the draw method
	 * 
	 */
	public void draw(Graphics g)
	{
		

		super.draw(g);
	}
	
	public boolean loadFrames()
	{
		frames = new BufferedImage[1];
		try
		{
			frames[0] = ImageIO.read(new File(LOAD_FILE));
		}
		catch(IOException e)
		{
			System.out.println("Yo");
		}
		return true;
	}
	
	
	
	

}
