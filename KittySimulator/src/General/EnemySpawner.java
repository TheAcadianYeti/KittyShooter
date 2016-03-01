package General;
import Enemies.*;
import java.util.*;
import java.awt.Point;
/**
 * Manages spawning all enemies in our game, creates them using an array of given position to start attacking
 * @author Patty
 *
 */
public class EnemySpawner 
{
	private final int SPAWN_NUM = 50;
	private MainPanel controlHub;
	private Sprite mainCharacter;
	private Sprite[] enemyArray;
	private Point[] spawnPoints;
	public EnemySpawner(MainPanel controlHub, Sprite mainChar)
	{
		this.controlHub = controlHub;
		//Just one enemy for now
		enemyArray = new Sprite[1];
		spawnPoints = new Point[10];
		
		this.mainCharacter = mainChar;
		initialSetup();
	}
	
	
	
	/*
	 * Holds an update method, called every frame
	 */
	public void update()
	{
		//Pick a ranom number between 1 and 100
		//If it's the spawn number, spwan enemy
	}
	
	/**
	 * Create enemy method, creates a new sprite with one of the random locations and adds them to the world there
	 * 
	 * For now just kamikaze koi
	 */
	public void createEnemy()
	{
		Random random = new Random();
		int index = random.nextInt(10);
		KamikazeKoi koi = new KamikazeKoi(spawnPoints[index].x,spawnPoints[index].y,0, mainCharacter);
		koi.setFrames(enemyArray[0].getFrames());
		controlHub.addActor(koi);
	}
	
	
	/**
	 * Initial setup method,need to load all of our sprites in and give them frames that way we don't need to recreate the 
	 * frames each time
	 */
	public void initialSetup()
	{
		KamikazeKoi koi = new KamikazeKoi(0, 0, 0, null);
		koi.loadFrames();
		enemyArray[0] = koi;
		//Add all the points
		spawnPoints[0] = new Point(0, 0);
		spawnPoints[1] = new Point(200, 200);
		spawnPoints[2] = new Point(100, 1000);
		spawnPoints[3] = new Point(500, 500);
		spawnPoints[4] = new Point(750, 100);
		spawnPoints[5] = new Point(100, 2000);
		spawnPoints[6] = new Point(100, 200);
		spawnPoints[7] = new Point(50, 50);
		spawnPoints[8] = new Point(1, 1);
		spawnPoints[9] = new Point(2000, 100);
	}
	
}
