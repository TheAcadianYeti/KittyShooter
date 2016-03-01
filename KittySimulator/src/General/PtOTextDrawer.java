package General;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.io.*;
import java.awt.image.*;

/**
 * Contains static methods that allow us to draw text on the screen with our default font
 * @author Patty
 *
 */
public class PtOTextDrawer 
{
	private static final String NUMBER_FILE = "Sprites/UI/Font/NUMBERS.png";
	private static BufferedImage[] numbers = new BufferedImage[11];
	private static final int KERNING = 5, SLASH = 10;
	/*
	 * Making it so you can't instantiate one
	 */
	private PtOTextDrawer(){};

	
	
	
	/**
	 * Draw String method, takes a string and draws it at the given position with the given text size
	 * 
	 */
	public static void drawString(int xPos, int yPos, int textSize, String text, Graphics g)
	{
		//Starting from the xPos need to go ahead and start drawing
		for(int i = 0; i < text.length(); i++)
		{
			switch(text.charAt(i))
			{
			case '0' :
				g.drawImage(numbers[0], xPos, yPos, textSize, textSize, null);
				break;
			case '1' :
				g.drawImage(numbers[1], xPos, yPos, textSize, textSize, null);
				break;
			case '2' : 
				g.drawImage(numbers[2], xPos, yPos, textSize, textSize, null);
				break;
			case '3' :
				g.drawImage(numbers[3], xPos, yPos, textSize, textSize, null);
				break;
			case '4' :
				g.drawImage(numbers[4], xPos, yPos, textSize, textSize, null);
				break;
			case '5' :
				g.drawImage(numbers[5], xPos, yPos, textSize, textSize, null);
				break;
			case '6' : 
				g.drawImage(numbers[6], xPos, yPos, textSize, textSize, null);
				break;
			case '7' :
				g.drawImage(numbers[7], xPos, yPos, textSize, textSize, null);
				break;
			case '8' :
				g.drawImage(numbers[8], xPos, yPos, textSize, textSize, null);
				break;
			case '9' :
				g.drawImage(numbers[9], xPos, yPos, textSize, textSize, null);
				break;
			case '/' :
				g.drawImage(numbers[SLASH], xPos, yPos, textSize, textSize, null);
				break;
			}
			xPos += textSize - KERNING;
		}
	}
	
	/**
	 * Loads font into array
	 */
	public static void loadFont()
	{
		try
		{
			File imageFile = new File(NUMBER_FILE);
			//We have the whole file now, we just need to go ahead and parse the image.  Each image is 20 width and height
			BufferedImage wholeImage = ImageIO.read(imageFile);
			int xPos = 0, yPos = 0;//The initial x and y pos
			int width = 20, height = 20;
			//Ten images
			for(int i = 0; i < 11; i++)
			{
				if(xPos >= wholeImage.getWidth())
				{
					xPos = 0;
					yPos += 20;
				}
				
				numbers[i] = wholeImage.getSubimage(xPos, yPos, width, height);
				xPos += 20;
			}
		}
		catch(IOException e)
		{
			System.err.println("Error loading font");
		}
	}
}
