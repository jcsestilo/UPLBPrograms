/*************************************************************************************************************************
 *
 * CMSC 22 Object-Oriented Programming
 * Abstraction: Everwing Example
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
 * Clover 		Increase the player's bullet level by 5 for the rest of the game round
 * 
 * (c) Institute of Computer Science, CAS, UPLB
 * @author Miyah Queliste
 *
 *************************************************************************************************************************/
package everwing;
import java.util.Random;

public class Monster {
	
	// attributes should be private!
	private int type; 		
	private int health;

	// constants are public
	public final static int RED_MONSTER = 0;
	public final static int GREEN_MONSTER = 1;
	public final static int RED_HEALTH = 10;
	public final static int GREEN_HEALTH = 50;

	// access modifier for constructors depend on the Class which will instantiate it. For Monster, it should be public
	public Monster(){
		Random r = new Random();	// instantiate a Random object
		this.type = r.nextInt(2);	// randomize int values from 0-1

		this.health = this.type==Monster.RED_MONSTER?Monster.RED_HEALTH:Monster.GREEN_HEALTH;
	}

	// Setter for the health of this monster
	// setHealth(int) is set to default access modifier since its caller is Guardian. No need to set it to public
	void setHealth(int damage){
		this.health -= damage;
	}

	public void viewState(){
		System.out.println("\nMONSTER STATE:");
		System.out.println("type: "+this.type);
		System.out.println("health: "+this.health);
	}
}