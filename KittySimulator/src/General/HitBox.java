package General;
import java.util.*;

public class HitBox 
{
	private HitBoxType boxType;
	private int xPos, yPos, width, height;
	//Designing this to be either a circle or a box type hit box.
	private boolean isCircle;
	/**
	 * Useful for differentiating between typesof hit boxes 
	 * @author Patty
	 *
	 */
	private enum HitBoxType
	{
		CHARACTER, ATTACK, TRIGGER, ENEMY;
	}
	
	public HitBox(int xPos, int yPos, int width, int height, boolean isCircle)
	{
		this.isCircle = isCircle;
		this.xPos = xPos;
		this.yPos = yPos;
		this.width = width;
		this.height = height;
	}
	
	public boolean didHit(HitBox otherBox)
	{
		//First off, are we dealing with a circle or a rectangle?  For now assume rectangle
		//First check x
		if(Math.abs(xPos - otherBox.getX()) <= width)
		{
			//Then are hitting on the x plane, must check the y
			if(Math.abs(yPos - otherBox.getY()) <= height)
			{
				//Should be good
				System.out.println("We have a hit!");
				return true;
			}
		}
			return false;
	}

	public int getX() {
		return xPos;
	}

	public void setX(int xPos) {
		this.xPos = xPos;
	}
	
	public int getY()
	{
		return yPos;
	}
	public void setY(int yPos)
	{
		this.yPos = yPos;
	}
	
	public boolean isCircle()
	{
		return isCircle;
	}

}
