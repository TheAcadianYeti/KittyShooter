package General;
import java.util.*;
import Enemies.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.*;

import MainCharacter.Kitty;

/**
 * Basic Camera class, encapsulates all sprites as "actors" and updates them based on relative position of those actors
 * @author Patty
 *
 */
public class Camera 
{
	private int worldWidth, worldHeight, xPos = 0, yPos = 0, height, width;
	private final int DRAWING_BOUNDS = 20;
	//Our set of actors needed to draw on screen
	private ArrayList<Sprite> actors;
	private ArrayList<KeyEvent> keys;
	private Sprite trackedActor;
	private ArrayList<Sprite>culledActors;
	/**
	 * Constructor, we have the width and height of the world, focusActor represents the actor we are tracking
	 * 
	 * 
	 */
	public Camera(int width, int height, int worldWidth, int worldHeight, Sprite focusActor)
	{
		actors = new ArrayList<Sprite>();
		culledActors = new ArrayList<Sprite>();
		trackedActor = focusActor;
		actors.add(trackedActor);
		this.worldWidth = worldWidth;
		this.worldHeight = worldHeight;
		this.width = width;
		this.height = height;
		keys = new ArrayList<KeyEvent>();
	}
	
	
	/**
	 * Method to update our x and y position based on our focus actor, trying to keepthem centered
	 */
	public void update()
	{
		//X to test to see if we update or not
		int testX  = trackedActor.getX() - width/2;
		if(!(testX >= worldWidth - width) && !(testX < 0))
		{
			this.xPos = testX;
		}	
		
		//Same with the y
		int testY = trackedActor.getY() - height/2;
		if(!(testY >= worldHeight - height) && !(testY < 0))
		{
			this.yPos = testY;
		}
		
	}
	
	
	/**
	 *	Draw method, draws things relative to the camera
	 * @param g
	 */
	public void draw(Graphics g)
	{
		
		cullActors();
		for(Sprite currentActor : actors)
		{
			//Draws all actors relative to camera positon, will not draw if they are far enough outside the screen
			//Always update, dont always draw
			currentActor.update();	
		}
		for(Sprite currentActor : culledActors)
		{
			currentActor.drawAt(g, currentActor.getX() - xPos, currentActor.getY() - yPos);
		}
	}
	
	
	/**
	 * GEtters and setters for x
	 * 
	 */
	public int getX(){return xPos;}
	public void setX(int x){this.xPos = x;}
	

	public int getY(){return yPos;}
	public void setY(int y){this.yPos = y;}
	
	/**
	 * The camera cshould be giving relative mouse update to it's position
	 */
	public void mouseUpdate(MouseEvent event)
	{
		//Create a new relative point
		Point point = new Point(event.getX() + xPos, event.getY() + yPos);
		((Kitty)trackedActor).mouseHasMoved(point);
	}
	
	/**
	 * Add actor method, adds an actor into the world at the desired xPos and yPos,
	 * note the xPos and yPos are purely based in real world x and y, and must be translated into relative to camera x and y
	 */
	public void addActor(Sprite actor)
	{
		actors.add(0, actor);		
		
	}
	
	/**
	 * Cull method, sets up the culledActors list in order to only draw sprites who are currently on screen, we still want to update everything though
	 */
	public void cullActors()
	{
		culledActors.clear();
		for(Sprite sprite : actors)
		{
			if(sprite.getX() - xPos >= -DRAWING_BOUNDS && sprite.getX() - xPos <= xPos + worldWidth + DRAWING_BOUNDS)
			{
				//We're good on the xPosition
				if(sprite.getY() - yPos >= -DRAWING_BOUNDS && sprite.getY() - yPos <= yPos + worldHeight +DRAWING_BOUNDS)
				{
					//Add to our actors
					culledActors.add(sprite);
				}
			}
		}
		
	}
	
}
