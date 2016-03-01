package MainCharacter;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import  General.*;
public class Kitty extends Sprite
{
	//private final String LOAD_FILE = "Sprites/Fly/fly_load.txt";
	private final String LOAD_FILE = "Sprites/Kitty/kitty_load";
	//private final String LOAD_FILE = "Sprites/Koi/koi_load.txt";
	//10 degree offset to each spread bullet
	private static  final int SPEED = 5, WIDTH = 20, HEIGHT = 20, SPREAD_DEGREE_OFFSET = 10, ROLL_TIME = 10, ROLL_SPEED = 10, ROLL_DELAY = 200;
	//Set of finals for the  
	public final boolean POOP = true;
	
	//Used to not have to load the frames each time
	public static Hashtable<BulletEnum, Bullet> bullets;
	
	private boolean firing = false, firingLaser = false, rolling = true;
	
	//Bullet delay represents the time between each bullet
	private int speedMultiplier = 1, bulletDelay = 0, currRollTime, rollDelay, ammoSupply = 1;
	private double currentRollAngle;
	private BulletEnum  currentBullet; //Represeents teh current bullet type being used
	private Point currentMouseEvent = null;
	
	private Laser lasor = null;
	private ArrayList<Bullet> bulletsOnScreen = new ArrayList<Bullet>();
	private ArrayList<Integer> kittyArray= new ArrayList<Integer>();
	private MainPanel controlHub;
	//Need the panel in order to be able to create the UI
	public Kitty(int initX, int initY, int angle, int delay, MainPanel controlHub)
	{
		super(WIDTH, HEIGHT, initX, initY, angle, delay);
		currentBullet = BulletEnum.DEFAULT;
		bullets = new Hashtable<BulletEnum, Bullet>();
		this.controlHub = controlHub;
		
		//Set the world width and height in sprite class
		super.setWorldHeight(controlHub.getWorldHeight());
		super.setWorldWidth(controlHub.getWorldHeight());
		

		
		//Have us reverse frames too
		reverseAnimation = true;
		//Also create the UI
		loadFrames();
	}

	
	/**
	 * Method to change the mouse event being used, big difference
	 */
	public void mouseHasMoved(Point event)
	{
		currentMouseEvent = event;
	}
	
	/**
	 * Method to update rotation based off of mouse position in panel
	 * Also Matt is stooooopid
	 * @param event
	 */
	public void mouseUpdate()
	{
		
		//Get position on screen relative to self, and update rotation
		//Calculate relative position
		double relativeX = currentMouseEvent.x - xPos;
		double relativeY = currentMouseEvent.y - yPos;
		//Calculate angle
		double relativeAngle = Math.atan2(relativeY, relativeX);
		super.setAngle(relativeAngle);
	}
	
	
	/**
	 * Toggles firing on, meaning this character is currently firing
	 */
	public void leftMousePress()
	{
		//Toggle fire on
		firing = true;
	}
	
	/**
	 * Toggles firing off, meaning this character stops firing
	 */
	public void leftMouseLift()
	{
		//Toggle firing off
		firing = false;
		if(firingLaser && currentBullet == BulletEnum.LASER)
		{
			firingLaser = false;
		}
	}
	
	
	/**
	 * Toggle bullet, changes bullet type in use
	 */
	public void changeAmmo(BulletEnum newType)
	{
		//Need to make sure to toggle laser
		if(firingLaser)
		{
			firingLaser = false;
		}
		
		
		this.currentBullet = newType;
		switch(newType)
		{
			case SPREAD :
				ammoSupply = SpreadBullet.AMMO_COUNT;
				break;
			case LASER :
				ammoSupply = Laser.AMMO_COUNT;
				break;
			case DEFAULT :
				ammoSupply = 1;
				break;
		}
	}
	
	
	/**
	 * Method to move kitty, 
	 */
	public void addKey(KeyEvent kittyKey)
	{

		Integer keyCode = kittyKey.getKeyCode();
		if(kittyKey.getKeyChar() == 'r' && rollDelay <= 0)
		{
			rolling = true;
			//Determine which buttons are being pushed to figure out the angle
			
			currentRollAngle = angle;
			currRollTime = ROLL_TIME;
			rollDelay = ROLL_DELAY;
		}
		else if(!kittyArray.contains(keyCode))
		{
			kittyArray.add(keyCode);
		}
	}
	
	public void removeKey(KeyEvent kittyKey)
	{
		Integer keyCode = kittyKey.getKeyCode();
		kittyArray.remove(keyCode);
	}
	
	
	/**
	 * Take damage method, where we just subtract on the main char's life
	 */
	public void takeDamage(double dmg)
	{
		
	}
	
	
	
	/**
	 * Overriding the update method, nothing for now
	 */
	public void update()
	{
		
	}
	
	
	/**
	 * Overriding the drawAt method, it is what is mostly going to be used to override the drawAt method, 
	 *For now we'll leave this here but should have delegated things into two seperate methods woops...
	 */
	public void drawAt(Graphics g, int xPos, int yPos)
	{

			//If we have moved the mouse once, update our angle and such
			if(currentMouseEvent != null)
			{
				mouseUpdate();
			}
			
			int xSpeed, ySpeed;
			if(rolling)
			{
				currRollTime--;
				if(currRollTime <= 0)
				{
					rolling = false;
				}
				//Get the current angle
		
				//Roll constant will be two times speed
				
				xSpeed = (int)(ROLL_SPEED * Math.cos(currentRollAngle));
				ySpeed = (int)(ROLL_SPEED * Math.sin(currentRollAngle));
				
			}
			else
			{
				
				xSpeed= 0;
				ySpeed = 0;
				for(int i = 0; i < kittyArray.size(); i++)
				{
					if(kittyArray.get(i) == KeyEvent.VK_DOWN)
					{
						ySpeed += SPEED;
					}	
					else if(kittyArray.get(i) == KeyEvent.VK_UP)
					{
						ySpeed -= SPEED;
					}
					else if(kittyArray.get(i) == KeyEvent.VK_LEFT)
					{
						xSpeed -= SPEED;
					}
					else if(kittyArray.get(i) == KeyEvent.VK_RIGHT)
					{
						xSpeed += SPEED;
					}
				}
				//Checkking to if we are firing
				if(firing)
				{
					//If we are, then we need to see if it's time to fire another bullet
					if(bulletDelay <= 0)
					{
						switch(currentBullet)
						{
						case  DEFAULT :
							bulletDelay = DefaultBullet.DELAY;
							DefaultBullet newBull = new DefaultBullet(xPos, yPos, angle);
							newBull.setFrames(bullets.get(BulletEnum.DEFAULT).getFrames());
							bulletsOnScreen.add(newBull);
							break;
						case SPREAD :
							bulletDelay = SpreadBullet.DELAY;
							
							//Update that we fired a bullet
							controlHub.firedBullet();
							//Need to add 5 bullets to this
							//Calculate double angle offset
							//Really gross making spread
							double angleOffset = (SPREAD_DEGREE_OFFSET * Math.PI) / 180.0;
							//Creating our bullets
							SpreadBullet bullet1 = new SpreadBullet(xPos, yPos, angle);
							SpreadBullet bullet2 = new SpreadBullet(xPos, yPos, angle + angleOffset);
							SpreadBullet bullet3 = new SpreadBullet(xPos, yPos, angle + angleOffset + angleOffset);
							SpreadBullet bullet4 = new SpreadBullet(xPos, yPos, angle - angleOffset);
							SpreadBullet bullet5 = new SpreadBullet(xPos, yPos, angle - angleOffset - angleOffset);
							//Giving them frames
							bullet1.setFrames(bullets.get(BulletEnum.SPREAD).getFrames());
							bullet2.setFrames(bullets.get(BulletEnum.SPREAD).getFrames());
							bullet3.setFrames(bullets.get(BulletEnum.SPREAD).getFrames());
							bullet4.setFrames(bullets.get(BulletEnum.SPREAD).getFrames());
							bullet5.setFrames(bullets.get(BulletEnum.SPREAD).getFrames());
							//Adding them to the screen
							bulletsOnScreen.add(bullet1);
							bulletsOnScreen.add(bullet2);
							bulletsOnScreen.add(bullet3);
							bulletsOnScreen.add(bullet4);
							bulletsOnScreen.add(bullet5);
							break;
						case LASER : 
							bulletDelay = Laser.DELAY;
							//Update that we fired
							
							
							if(!firingLaser)
							{
								Laser laser = new Laser(xPos, yPos, angle);
								lasor = laser;
								laser.setFrames(bullets.get(BulletEnum.LASER).getFrames());
								
								bulletsOnScreen.add(laser);
								firingLaser = true;
							}
							else
							{
								if(lasor != null)
								{
									controlHub.firedBullet();
									lasor.update(xPos, yPos, angle);
								}
							}
						
						
						}

					
					}
				}
			}	//Subtracting a second from the bullet delay
			bulletDelay--;
			//Also need to update the rolldelay
			if(rollDelay > 0)
			{
				rollDelay--;
			}
		
			
			/**
			 * Drawing the bullets
			 */
			for(int i = 0; i < bulletsOnScreen.size(); i++)
			{
				Bullet currBull = bulletsOnScreen.get(i);
				currBull.update();
				currBull.draw(g);
				if(currBull.getDuration() <= 0)
				{
					bulletsOnScreen.remove(i);
				}
				
			}
			

			
			
			super.update(xSpeed, ySpeed);
			super.drawAt(g, xPos, yPos);

	}
	/**
	 * Overriding draw method in order to update based off of what is keys are being pressed
	 */
	public void draw(Graphics g)
	{

		//If we have moved the mouse once, update our angle and such
		if(currentMouseEvent != null)
		{
			mouseUpdate();
		}
		
		int xSpeed, ySpeed;
		if(rolling)
		{
			currRollTime--;
			if(currRollTime <= 0)
			{
				rolling = false;
			}
			//Get the current angle
	
			//Roll constant will be two times speed
			xSpeed = (int)(ROLL_SPEED * Math.cos(currentRollAngle));
			ySpeed = (int)(ROLL_SPEED * Math.sin(currentRollAngle));
			
		}
		else
		{
			
			xSpeed= 0;
			ySpeed = 0;
			for(int i = 0; i < kittyArray.size(); i++)
			{
				if(kittyArray.get(i) == KeyEvent.VK_DOWN)
				{
					ySpeed += SPEED;
				}	
				else if(kittyArray.get(i) == KeyEvent.VK_UP)
				{
					ySpeed -= SPEED;
				}
				else if(kittyArray.get(i) == KeyEvent.VK_LEFT)
				{
					xSpeed -= SPEED;
				}
				else if(kittyArray.get(i) == KeyEvent.VK_RIGHT)
				{
					xSpeed += SPEED;
				}
			}
			//Checkking to if we are firing
			if(firing)
			{
				//If we are, then we need to see if it's time to fire another bullet
				if(bulletDelay <= 0)
				{
					switch(currentBullet)
					{
					case  DEFAULT :
						bulletDelay = DefaultBullet.DELAY;
						DefaultBullet newBull = new DefaultBullet(xPos, yPos, angle);
						newBull.setFrames(bullets.get(BulletEnum.DEFAULT).getFrames());
						bulletsOnScreen.add(newBull);
						break;
					case SPREAD :
						bulletDelay = SpreadBullet.DELAY;
						
						//Update that we fired a bullet
						controlHub.firedBullet();
						//Need to add 5 bullets to this
						//Calculate double angle offset
						//Really gross making spread
						double angleOffset = (SPREAD_DEGREE_OFFSET * Math.PI) / 180.0;
						//Creating our bullets
						SpreadBullet bullet1 = new SpreadBullet(xPos, yPos, angle);
						SpreadBullet bullet2 = new SpreadBullet(xPos, yPos, angle + angleOffset);
						SpreadBullet bullet3 = new SpreadBullet(xPos, yPos, angle + angleOffset + angleOffset);
						SpreadBullet bullet4 = new SpreadBullet(xPos, yPos, angle - angleOffset);
						SpreadBullet bullet5 = new SpreadBullet(xPos, yPos, angle - angleOffset - angleOffset);
						//Giving them frames
						bullet1.setFrames(bullets.get(BulletEnum.SPREAD).getFrames());
						bullet2.setFrames(bullets.get(BulletEnum.SPREAD).getFrames());
						bullet3.setFrames(bullets.get(BulletEnum.SPREAD).getFrames());
						bullet4.setFrames(bullets.get(BulletEnum.SPREAD).getFrames());
						bullet5.setFrames(bullets.get(BulletEnum.SPREAD).getFrames());
						//Adding them to the screen
						bulletsOnScreen.add(bullet1);
						bulletsOnScreen.add(bullet2);
						bulletsOnScreen.add(bullet3);
						bulletsOnScreen.add(bullet4);
						bulletsOnScreen.add(bullet5);
						break;
					case LASER : 
						bulletDelay = Laser.DELAY;
						//Update that we fired
						
						
						if(!firingLaser)
						{
							Laser laser = new Laser(xPos, yPos, angle);
							lasor = laser;
							laser.setFrames(bullets.get(BulletEnum.LASER).getFrames());
							
							bulletsOnScreen.add(laser);
							firingLaser = true;
						}
						else
						{
							if(lasor != null)
							{
								controlHub.firedBullet();
								lasor.update(xPos, yPos, angle);
							}
						}
					
					
					}

				
				}
			}
		}	//Subtracting a second from the bullet delay
		bulletDelay--;
		//Also need to update the rolldelay
		if(rollDelay > 0)
		{
			rollDelay--;
		}
	
		
		/**
		 * Drawing the bullets
		 */
		for(int i = 0; i < bulletsOnScreen.size(); i++)
		{
			Bullet currBull = bulletsOnScreen.get(i);
			currBull.draw(g);
			if(currBull.getDuration() <= 0)
			{
				bulletsOnScreen.remove(i);
			}
			
		}
		

		
		
		super.update(xSpeed, ySpeed);
		super.draw(g);
	}
	
	/**
	 * Sets up the kitty sprite and initial position
	 * @return
	 */
	public boolean loadKitty()
	{
		return POOP;
	}
	
	/**
	 * Implementing load method, loads our sprites into array
	 */
	public boolean loadFrames()
	{
		//Load frames will also create all our bullet types and load them into a ddictionary
		frames = MainController.loadFrames(LOAD_FILE);
		if(frames == null)
		{
			System.err.println("Failed to load main character frames");
			System.exit(1);
		}
		//Also need to create all static versions of the bullets
		DefaultBullet standard = new DefaultBullet(0, 0, 0);
		standard.loadFrames();
		SpreadBullet spread = new SpreadBullet(0, 0, 0);
		spread.loadFrames();
		Laser laser = new Laser(0, 0, 0);
		laser.loadFrames();
		bullets.put(BulletEnum.DEFAULT, standard);
		bullets.put(BulletEnum.SPREAD, spread);
		bullets.put(BulletEnum.LASER,  laser);
		
		return POOP;
		
	}

	
	/**
	 * Declaration of enum types for bullets
	 * 
	 */
	public enum BulletEnum
	{
		SPREAD, DEFAULT, LASER;
	}
	

}
