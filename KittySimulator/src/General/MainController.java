package General;
import java.util.*;
import java.io.*;
import java.awt.image.*;

import javax.imageio.ImageIO;
public class MainController extends Thread
{
	private MainPanel paneToUpdate;
	
	public MainController(MainPanel paneToUpdate)
	{
		this.paneToUpdate = paneToUpdate;
	}
	
	public void run()
	{
		int count= 0;
		//Load fonts 
		PtOTextDrawer.loadFont();
		while(true && count < Integer.MAX_VALUE)
		{
			paneToUpdate.repaint();
			try
			{
				//We want this game to operate at 60 frames/second, so we'll operate at 
				//delays of 17 milliseconds...right?
				this.sleep(15);
			}
			catch(InterruptedException e)
			{
				System.out.println("Hrm");
			}
			count++;
		}
	}
	
	/**
	 * Static method that given the proper loadfile will get all frames from the specific
	 * folder and return them in an array of BufferedImages.
	 *
	 *Format should as follows
	 *First Line : Number of frames
	 *Every subsequent line : Name of file name of frame
	 *
	 * @param fileName
	 * @return
	 */
	public static BufferedImage[] loadFrames(String fileName)
	{
		try
		{
			Scanner scan = new Scanner(new File(fileName));
			int numOfFrames = scan.nextInt();
			//Getting rid of the start newLine
			scan.nextLine();
			int count = 0;
			BufferedImage[] frames = new BufferedImage[numOfFrames];
			//Reading all frames into the array
			while(count < numOfFrames)
			{
				String imageName = scan.nextLine();
				frames[count] = ImageIO.read(new File(imageName));
				count++;
			}
			scan.close();
			return frames;
		}
		catch(IOException e)
		{
			System.out.println("There was an error in building frame array\n" + e.getMessage());
			return null;
		}
	}
	
}
