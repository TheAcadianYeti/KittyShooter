package General;
import java.awt.*;
import Enemies.*;

import MainCharacter.*;
import MainCharacter.Kitty.BulletEnum;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.*;
/**
 * Should also have main panel draw the ui
 * @author Patty
 *
 */
public class MainPanel extends JPanel
{
	private PtOBackground bg;
	private EnemySpawner spawner;
	private final int WORLD_WIDTH = 2000, WORLD_HEIGHT = 2000;
	private KittyUI kittyUI;
	private Camera camera;
	private Kitty mainChar;
	private UndercoverMouse testChar;
	private ScratchPost testPost;
	private Sprite[] spriteArray = new Sprite[1];
	public MainPanel(int width, int height)
	{

		//Setting up sizing
		this.setPreferredSize(new Dimension(width, height));
		initialSetup();
		//Setting up on screen elements 
		kittyUI = new KittyUI(this, width, height);
		testChar = new UndercoverMouse(200,200);
		testChar.loadFrames();

		//this.setBackground(Color.BLACK);
		//Setting up key listener
		this.addKeyListener(new keyListener());
		this.addMouseMotionListener(new mouseMotionListener());
		this.addMouseListener(new mouseClickListener());
		this.setFocusable(true);
		this.requestFocus();
		
		
		
	}
	
	
	
	/**
	 * Add actor method, receives a sprite and adds it to the camera
	 */
	public void addActor(Sprite actor)
	{
		camera.addActor(actor);
	}
	
	
	/**
	 * Ovewriting the HMWMFMWFWQEMFQF method which allows us to draw our own stuff
	 */
	public void paintComponent(Graphics page)
	{
		super.paintComponent(page);
		//Need to update everything
		camera.update();
		bg.update();
		spawner.update();
		//testChar.update();
		
		bg.draw(page);
		camera.draw(page);
		//testChar.draw(page);
		kittyUI.draw(page);
		
		
	}
	
	
	
	/**
	 * Method for when the character runs out of ammo
	 */
	public void ranOutOfAmmo()
	{
		mainChar.changeAmmo(BulletEnum.DEFAULT);
		kittyUI.changeAmmo(KittyUI.DEFAULT, 1);
	}
	
	
	/**
	 * Bullet fired method, changes UI amonut by one
	 */
	public void firedBullet()
	{
		kittyUI.firedBullet();
	}
	
	
	/**
	 * Player died method, indicates when the kitty has no more health yet,
	 * will cause the game to finish
	 */
	public void playerDied()
	{
		System.err.println("Player is ded");
	}
	
	
	/**
	 * Initial setup, loads all actosr onto scene
	 */
	public void initialSetup()
	{
		mainChar = new Kitty(250, 250, 60, 2, this);
		
		//Create camera
		camera = new Camera(1000, 700, WORLD_WIDTH, WORLD_HEIGHT, mainChar);
		
		
		//Make a new background
		bg = new PtOBackground(camera);
		
		//Making enemy spawner
		spawner = new EnemySpawner(this, mainChar);
		

	}
	
	/**
	 * Need a mouse listener class, keeps track of where the mouse pointer is on screen
	 * 
	 */
	private class mouseMotionListener implements MouseMotionListener
	{

		@Override
		public void mouseDragged(MouseEvent arg0)
		{
			// TODO Auto-generated method stub
			camera.mouseUpdate(arg0);
		}

		@Override
		public void mouseMoved(MouseEvent arg0) 
		{
			// TODO Auto-generated method stub
			//Pass the event to the mainChar
			camera.mouseUpdate(arg0);
		}

		
		
	}
	
	/**
	 * Getters
	 * 
	 */
	public int getWorldHeight(){return WORLD_HEIGHT;}
	public int getWorldWidth(){return WORLD_WIDTH;}
	
	/**
	 * Making the key listener class
	 */
	private class keyListener implements KeyListener
	{

		@Override
		public void keyPressed(KeyEvent arg0) 
		{
			// TODO Auto-generated method stub
			mainChar.addKey(arg0);
			
			
		}

		@Override
		public void keyReleased(KeyEvent arg0) 
		{
			// TODO Auto-generated method stub
			
			mainChar.removeKey(arg0);
		}

		@Override
		public void keyTyped(KeyEvent arg0) 
		{
			// If key equals G, switch to spread, if it equals h switch tod efault
			if(arg0.getKeyChar() == 'g')
			{
				mainChar.changeAmmo(Kitty.BulletEnum.SPREAD);
				kittyUI.changeAmmo(KittyUI.SPREAD, SpreadBullet.AMMO_COUNT);
				
			}
			else if(arg0.getKeyChar() == 'h')
			{
				mainChar.changeAmmo(Kitty.BulletEnum.DEFAULT);
				kittyUI.changeAmmo(KittyUI.DEFAULT, 1);
			}
			else if(arg0.getKeyChar() == 'l')
			{
				mainChar.changeAmmo(Kitty.BulletEnum.LASER);
				kittyUI.changeAmmo(KittyUI.LASER, Laser.AMMO_COUNT);
			}
			else if(arg0.getKeyChar() == 'q')
			{
				kittyUI.takeDamage(5);
			}
			else if(arg0.getKeyChar() == 'z')
			{
				spawner.createEnemy();
			}
			
		}
		
	
	}
	
	/**
	 * Making our mouse listener for clicks
	 * 
	 */
	private class mouseClickListener implements MouseListener
	{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			mainChar.leftMousePress();
		}

		@Override
		public void mouseReleased(MouseEvent e) 
		{
			// TODO Auto-generated method stub
			mainChar.leftMouseLift();
			
		}
		
	}
}
