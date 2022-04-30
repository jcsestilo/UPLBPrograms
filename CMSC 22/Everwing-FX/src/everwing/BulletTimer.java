/*************************************************************************************************************************
 *
 * CMSC 22 Object-Oriented Programming
 * Everwing Example
 * Problem Domain: Everwing is a game where the goal is for the guardian to kill monsters by shooting them and collect coins.  
 * When a guardian is created, it is given a name and initially a score of 0. The guardian can move to the left or to the right. 
 * Monsters appear by waves and die when health reaches 0. 
 * When a monster dies, it becomes a coin or item that the guardian can collect. There is the red and the green type monster 
 * with 10 and 50 health respectively.
 * When the guardian hits a monster, the guardian dies.
 * Different items can be collected by the guardian:
 * Item 		Effect
 * Coin 		Adds +1 score and 1 coin
 * Purple Gem 	Adds +10 score and 10 coins
 * Clover 		Increase the player's bullet level by 5 for the 5 seconds
 * 
 * (c) Institute of Computer Science, CAS, UPLB
 * @author Miyah Queliste
 * @date 2018-05-10 
 *************************************************************************************************************************/
package everwing;

class BulletTimer extends Thread {
	private Guardian guardian;
	private int time;
	private final static int UPGRADED_TIME = 5;

	BulletTimer(Guardian guardian){
		this.guardian = guardian;
		this.time = BulletTimer.UPGRADED_TIME;
	}

	void refresh(){
		this.time = BulletTimer.UPGRADED_TIME;
	}

	/*
     * Counts down and downgrades the bullets after designated time
     * */
	private void countDown(){
		while(this.time!=0){
			try{
				Thread.sleep(1000);
				this.time--;
			}catch(InterruptedException e){
				System.out.println(e.getMessage());
			}
		}
		this.guardian.downgradeBullets();
	}

	@Override
	public void run(){
		this.countDown();
	}
}
