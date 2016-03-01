package MainCharacter;
import java.awt.*;
import java.io.*;
import java.awt.image.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import General.MainPanel;
import General.PtOTextDrawer;
/**
 * Represents the UI the kitty will have on screen, represents the health
 * and current ammo type
 * @author Patty
 *
 */
public class KittyUI
{
	private static final int START_HEALTH = 100, HEALTH_BAR_OFFSET =10, BULLET_OFFSET = 75, HEIGHT = 100, WIDTH = 300, ICON_SIZE = 50, TEXT_OFFSET= 75;
	public static final int LASER = 0, SPREAD = 1, DEFAULT = 2, AMMO_TYPES = 3;
	private final String MAIN_DIR = "Sprites/UI/KittyUI/";
	
	//Will affect the width
	private double currentHealth;
	private Point topLeft, bottomRight;
	private BufferedImage healthUIFrame, healthUIBar;
	private BufferedImage[] ammoIcons;
	//Also need a reference tothe main controller
	private MainPanel controlHub;
	private int currentAmmoType, maxCount = 1, currentAmmo = 1;
	
	
	//Use the panel to place all elements of the UI,need to pass in paneWidth and height due to  drawing order
	public KittyUI(MainPanel pane,int paneWidth, int paneHeight)
	{
		controlHub = pane;
		currentHealth = START_HEALTH;
		ammoIcons = new BufferedImage[AMMO_TYPES];
		topLeft = new Point(pane.getX(), pane.getY());
		
		bottomRight = new Point(pane.getX() + paneWidth, pane.getY() + paneHeight);
		
		currentAmmoType = DEFAULT;
		initSetup();
	}
	
	
	/**
	 * Updates bullet count
	 * 
	 */
	public void firedBullet()
	{
		currentAmmo--;
		if(currentAmmo <= 0)
		{
			controlHub.ranOutOfAmmo();
		}
	}
	/**
	 * Method to change ammo
	 */
	public void changeAmmo(int ammoType, int ammoCount)
	{
		currentAmmo = ammoCount;
		maxCount = ammoCount;
		currentAmmoType = ammoType;
	}
	
	/**
	 * Method to change damage meter
	 */
	public void takeDamage(double dmg)
	{
		if(currentHealth > 0)
		{
			this.currentHealth-= dmg;
		}
		else if(currentHealth <= 0)
		{
			controlHub.playerDied();
		}
	}
	
	/**
	 * Paint component override
	 */
	public void draw(Graphics g)
	{
		
		//Draw bar first
		g.drawImage(healthUIBar, topLeft.x + HEALTH_BAR_OFFSET, topLeft.y + HEALTH_BAR_OFFSET, (int)((currentHealth/100) * WIDTH), HEIGHT, null);
		
		//Draw the frame
		g.drawImage(healthUIFrame, topLeft.x + HEALTH_BAR_OFFSET, topLeft.y + HEALTH_BAR_OFFSET,  WIDTH, HEIGHT, null);
		
		//Time to draw the icon
		g.drawImage(ammoIcons[currentAmmoType], bottomRight.x - BULLET_OFFSET, bottomRight.y - BULLET_OFFSET, ICON_SIZE, ICON_SIZE, null);
		
		PtOTextDrawer.drawString(bottomRight.x - BULLET_OFFSET - TEXT_OFFSET, bottomRight.y - BULLET_OFFSET + 10, 15, currentAmmo + "/" + maxCount, g);
		
	}
	
	/**
	 * Init setup method, loads in images
	 */
	private void initSetup()
	{
		try
		{
			//Load in health bar
			File image = new File(MAIN_DIR + "HEALTH_BAR.png");
			healthUIBar = ImageIO.read(image);
			//Load in frame
			image = new File(MAIN_DIR + "HEALTH_FRAME.png");
			healthUIFrame = ImageIO.read(image);
			
			//Loading bullet icons
			image = new File(MAIN_DIR + "DEFAULT_BULLET_ICON.png");
			ammoIcons[DEFAULT] = ImageIO.read(image);
			
			image = new File(MAIN_DIR + "LASER_ICON.png");
			ammoIcons[LASER] = ImageIO.read(image);
			
			image = new File(MAIN_DIR + "SPREAD_ICON.png");
			ammoIcons[SPREAD] = ImageIO.read(image);
		}
		catch(IOException e)
		{
			System.err.println("Error at " + e.getMessage());
		}
	}

}
