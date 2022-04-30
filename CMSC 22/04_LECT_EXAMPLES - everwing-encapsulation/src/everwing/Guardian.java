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

public class Guardian {
	
	// attributes should be private!
	private String name;
	private int score;
	private Bullet bullet; // aggregation relationship - a Guardian HAS-A Bullet
	private boolean gotClover; // will determine this Guardian's bullet type
	
	// even class attributes should be private
	private static int numOfGuardians;

	// access modifier for constructors depend on the Class which will instantiate it. For Guardian, it should be public
	public Guardian(String name) {
		this.name=name;
		this.score=0;				// this can be omitted
		this.bullet = null;			// this can be omitted too
		this.gotClover = false;		// and this one
		Guardian.numOfGuardians++;
	}

	// methods are initially set to private and then loosened up depending on the Class that call it
	public void collectCoin(){
	 	System.out.println("\n" + this.name + " got a coin!");
		this.score++;
	}

	public void shoot(Monster monster){
	 	System.out.println("\n" +this.name + " shoots a monster");
	 	// Every time the shoot method is called, a new bullet is created depending on the Guardians' gotClover attribute
	 	this.bullet = this.gotClover?new Bullet(Bullet.UPGRADED_BULLET):new Bullet(Bullet.ORDINARY_BULLET);

	 	// ORIGINAL CODE WITHOUT ENCAPSULATION:
	 	// int damage = this.bullet.damage;
	 	// monster.health -= damage; 

	 	// MODIFIED CODE USING SETTERS AND GETTERS:
	 	int val = this.bullet.getDamage();
	 	monster.setHealth(val);
	}
	
	public static int count(){
		return Guardian.numOfGuardians;
	}

	public void viewState(){
		System.out.println("\nGuardian");
		System.out.println("name: "+this.name);				// access instance attributes of this Guardian
		System.out.println("score: "+this.score);
		System.out.println("gotClover: "+this.gotClover);
	}
}

