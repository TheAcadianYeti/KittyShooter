import java.awt.*;

import javax.swing.*;

import General.MainController;
import General.MainPanel;

import java.util.*;


public class TestTest 
{
	public static void main(String[] args)
	{
		JFrame testFrame = new JFrame("Kitty test");
		testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MainPanel pane = new MainPanel(1000, 700);
		MainController control  = new MainController(pane);
		
		testFrame.setContentPane(pane);
		testFrame.pack();
		testFrame.setVisible(true);
		
		/*LEaving this in case of testing boxes again
		//Running some tests with hitboxes
		HitBox[] hitBoxTests = new HitBox[10];
		hitBoxTests[0] = new HitBox(1, 1, 10, 10, false);
		hitBoxTests[1] = new HitBox(5, 5, 3, 3, false);
		hitBoxTests[2] = new HitBox(2, 3, 4, 4, false);
		hitBoxTests[3] = new HitBox(5, 5, 1, 1, false);
		hitBoxTests[4] = new HitBox(8, 8, 12, 12, false);
		hitBoxTests[5] = new HitBox(9, 2, 20, 20, false);
		hitBoxTests[6] = new HitBox(7, 11, 1, 1, false);
		hitBoxTests[7] = new HitBox(3, 2, 10, 5, false);
		hitBoxTests[8] = new HitBox(3, 2, 5, 9, false);
		hitBoxTests[9] = new HitBox(0, 2, 1, 4, false);
		for(int i = 0; i < hitBoxTests.length; i++)
		{
			for(int j = 0; j < hitBoxTests.length; j++)
			{
				HitBox test1 = hitBoxTests[i];
				HitBox test2 = hitBoxTests[j];
				System.out.println("Hit box at " + test1.getX() + ", " + test1.getY() + " was " + test1.didHit(test2) + " in hitting hit box at " + test2.getX() + ", " + test2.getY());
			}
		}
		*/
		
		
		
		
		control.start();
	}

}
