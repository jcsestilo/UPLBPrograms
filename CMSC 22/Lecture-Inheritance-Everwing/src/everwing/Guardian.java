package everwing;

// package name

// import statements

/*************************************************************************
 * 
 * Abstraction - Class part 1
 * Everwing Game example
 * Description:
 * The Guardian class is one of the main characters in the Everwing game.
 * This is the initial implementation of class design based on 
 * the problem domain.
 * 
 * @author Mylah Rystie U. Anacleto
 * @date 2020-08-27 08:00
 *
 * (c) Institute of Computer Science, UPLB
 */

public class Guardian {

	// attribute(s)
	private String name;
	private int score;
	private static int numOfGuardians = 0;
	
	private Bullet bullet;  // Aggregation relationship with Bullet; Guardian HAS-A Bullet
	private int bulletType;
	

	// constructor(s)
	public Guardian(String name){
		System.out.println("Creating guardian " + name + " ...");
		this.name = name;
		this.score = 0;
		
		// need to initialize the bullet type here
		// types are usually declared as constants
		this.bulletType = Bullet.ORDINARY_BULLET;
		
		numOfGuardians++;
	}
	
	public Guardian(String name, int score){
		System.out.println("Creating guardian " + name + " ...");
		this.name = name;
		this.score = score;
		numOfGuardians++;
	}
	
	// method(s)
	
//	public void collectCoin() { 
//		System.out.println("\nGuardian " + this.name + " collected coin!");
//		this.score++; //as the problem domain specified
//	}
	
	public void shoot(Monster monster) {  // Association relationship with Monster; Monster is a parameter of this method
		
	 	System.out.println("\n" +this.name + " shoots a monster");
	 	// Every time the shoot method is called, a new bullet is created depending on the Guardians' bullet type
	 	this.bullet = new Bullet(this.bulletType); 

	 	this.bullet.viewState();

	 	// interaction of monster and guardian via bullet
	 	monster.setHealth(this.bullet.getDamage()); // decrease monster's health by this Guardian's bullet damage

		
	}

	
	void setScore(int change) {
		this.score+=change;
	}
	
	public void setBulletType(int bulletType) {
		this.bulletType = bulletType;
	}
	
	// let's create a class method
	public static int count() {	 // can only access attributes (and even methods) that are static
		return numOfGuardians;
	}
	
	public void viewState() {
		System.out.println("\nGuardian name: "+this.name);
		System.out.println("Guardian score: "+this.score); 
	}

	public void collectCoin(Coin coin) {

		System.out.println("\n" + this.name + " is collecting coin...");
		coin.affect(this);
	}

	public void collectGem(Gem gem) {
		System.out.println("\n" + this.name + " is collecting gem...");
		gem.affect(this);
	}

	public void collectClover(Clover clover) {
		System.out.println("\n" + this.name + " is collecting clover...");
		clover.affect(this);
	}

	

	
}
