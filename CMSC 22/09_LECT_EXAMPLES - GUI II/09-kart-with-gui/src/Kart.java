/*************************************************************************************************************************
 *
 * CMSC 22 Object-Oriented Programming
 * Multithreading Example (with GUI): Kart
 * 
 * (c) Institute of Computer Science, CAS, UPLB
 * @author Miyah Queliste
 *
 *************************************************************************************************************************/
import java.util.Random;

import javafx.scene.image.Image;

/**********************************************************************************************
* Kart class which may extend the Thread class or implement the Runnable interface.
***********************************************************************************************/

// If extending Thread class:
//public class Kart extends Thread {

// If implementing Runnable interface:
 public class Kart extends Sprite implements Runnable {
	private String name;
	private int distance;
	private static Kart winner;

	private final static int TOTAL_DISTANCE = 500;
	private final static Image KART_IMAGE = new Image("kart.png");	// you can use new Image("path",width,height,false,false); if you want to scale your image
	
	public Kart(int x, int y, String name){
		super(x,y, Kart.KART_IMAGE);
		this.name=name;
	}

	/*Class method*/
	public static Kart getWinner(){
		return Kart.winner;
	}

	public String getKartName(){
		return this.name;
	}

	/**********************************************************************************************
	* Overrides the run method of the Thread class or the Runnable interface
	* When the thread is started, makes the kart race.
	***********************************************************************************************/
	public void run(){
		this.race();
	}

	/**********************************************************************************************
	* Kart continuously moves and sleeps for 30 milliseconds until one kart wins.
	* If kart reaches the TOTAL_DISTANCE, it is set to be the winner.
	***********************************************************************************************/
	private void race(){
		while(true){
			this.move();
			
			/*If this kart reaches the finish line, try to set it as the winner.*/
			if(this.distance>=Kart.TOTAL_DISTANCE) {
				Kart.setWinner(this);
				break;
			}
			
			try {
				Thread.sleep(10);
			} catch (Exception e){}
		}
	}

	/**********************************************************************************************
	* A random value is genereated for the kart to move. 
	***********************************************************************************************/
	private void move(){
		Random r = new Random();
		int step = r.nextInt(5)+5;

		this.distance += step;
		this.setXPos(this.distance);
		System.out.println(this.name + " run " + this.distance + " meters.");
	}

	/*Class method*/
	private static boolean isRaceDone(){
		if(Kart.winner!=null) return true;
		else return false;
	}

	/*Class method*/
	private synchronized static void setWinner(Kart kart){
		if(!isRaceDone()){
			Kart.winner = kart;
		}
	}
}
