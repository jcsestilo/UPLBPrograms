/*************************************************************************************************************************
 *
 * CMSC 22 
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
	private int bulletType; // 0 - ordinary bullet, 1 - upgraded bullet
	
	// even class attributes should be private
	private static int numOfGuardians;

	// access modifier for constructors depend on the Class which will instantiate it. For Guardian, it should be public
	public Guardian(String name) {
		this.name=name;
		this.score=0;				// this can be omitted
		this.bullet = null;			// this can be omitted too
		this.bulletType = Bullet.ORDINARY_BULLET;		// and this one
		Guardian.numOfGuardians++;
	}

	public void shoot(Monster monster){
	 	System.out.println("\n" +this.name + " shoots a monster!");
	 	
	 	this.bullet = new Bullet(this.bulletType); 
	 	this.bullet.viewState();

	 	// ORIGINAL CODE WITHOUT ENCAPSULATION:
	 	// int val -= this.bullet.damage;
	 	// monster.health -= val; 

	 	// MODIFIED CODE USING SETTERS AND GETTERS:
	 	int val = this.bullet.getDamage();
	 	monster.setHealth(val);

	}

	public void collect(Item item){
		System.out.println("\n"+this.name + " has collected a "+item.type);
		item.affect(this);
	}

	// added setters
	void setScore(int change){
		this.score+=change;
	}

	void setBulletType(int type){
		this.bulletType=type;
	}
	
	public static int count(){
		return Guardian.numOfGuardians;
	}
	
	String getName() {
		return this.name;
	}

	public void viewState(){
		System.out.println("\nGUARDIAN STATE:");
		System.out.println("name: "+this.name);				// access instance attributes of this Guardian
		System.out.println("score: "+this.score);
		System.out.println("bulletType: "+this.bulletType);
	}
}

