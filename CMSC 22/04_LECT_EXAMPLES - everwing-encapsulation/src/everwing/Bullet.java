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

class Bullet {
	
	// attributes should be private!
	private int type; 		
	private int damage;

	// constants are public
	public final static int ORDINARY_BULLET = 0;
	public final static int UPGRADED_BULLET = 1;
	public final static int ORDINARY_DAMAGE = 10;
	public final static int UPGRADED_DAMAGE = 20;

	// access modifier for constructors depend on the Class which will instantiate it. For Bullet, it should be none or default
	Bullet(int type){

		this.type = type;
		this.damage = this.type==Bullet.ORDINARY_BULLET?Bullet.ORDINARY_DAMAGE:Bullet.UPGRADED_DAMAGE;
	}

	// Getter for the damage of this bullet
	int getDamage(){
		return this.damage;
	}
	
	// methods of Bullet are set to default since only Guardian is the user and Guardian is in the same package
	void viewState(){
		System.out.println("\nBULLET STATE:");
		System.out.println("type: "+this.type);
		System.out.println("damage: "+this.damage);
	}
}