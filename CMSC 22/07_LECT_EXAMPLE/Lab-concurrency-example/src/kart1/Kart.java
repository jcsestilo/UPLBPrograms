package kart1;

/*************************************************************************************************************************
 *
 * CMSC 22 Object-Oriented Programming
 * Multithreading Example: Kart
 * 
 * (c) Institute of Computer Science, CAS, UPLB
 * @author Miyah Queliste
 *
 *************************************************************************************************************************/
import java.util.Random;

/**********************************************************************************************
* Kart class which may extend the Thread class or implement the Runnable interface.
***********************************************************************************************/

// If extending Thread class:
public class Kart extends Thread {

// If implementing Runnable interface:
// public class Kart implements Runnable {
	private String name;
	private int distance;
	private static Kart winner;

	private final static int TOTAL_DISTANCE = 500;

	public Kart(String name){
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
	@Override
	public void run(){
		this.race();
	}
	
	// ---------x
	// -----------------x

	/**********************************************************************************************
	* Kart continuously moves and sleeps for 30 milliseconds until one kart wins.
	* If kart reaches the TOTAL_DISTANCE, it is set to be the winner.
	***********************************************************************************************/
	private void race(){
		while(true){
			this.move();
			
			/*If this kart reaches the finish line, try to set it as the winner.*/
			if(this.distance>=Kart.TOTAL_DISTANCE){
				Kart.setWinner(this);
				break;
			}

			try {
				Thread.sleep(5);
			} catch (Exception e){}
		}
	}

	/**********************************************************************************************
	* A random value is genereated for the kart to move. 
	***********************************************************************************************/
	private void move(){
		Random r = new Random();
		int step = r.nextInt(5)+10;

		this.distance += step;
		System.out.println(this.name + " ran " + this.distance + " meters.");
	}

	/*Class method*/
	private static boolean isRaceDone(){
		if(Kart.winner!=null) return true;
		else return false;
	}

	/*Class method*/
	private static void setWinner(Kart kart){
		if(!isRaceDone()){
			Kart.winner = kart;
		}
	}
}
