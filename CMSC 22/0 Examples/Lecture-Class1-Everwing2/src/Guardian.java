
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
 *
 * (c) Institute of Computer Science, UPLB
 */

public class Guardian {

	// attribute(s)
	String name;
	int score;
	static int numOfGuardians = 0;
	
	Bullet bullet;  // Aggregation relationship with Bullet; Guardian HAS-A Bullet
	int bulletType;
	

	// constructor(s)
	Guardian(String name){
		System.out.println("Creating guardian " + name + " ...");
		this.name = name;
		this.score = 0;
		
		// need to initialize the bullet type here
		// types are usually declared as constants
		this.bulletType = Bullet.ORDINARY_BULLET;
		
		numOfGuardians++;
	}
	
	Guardian(String name, int score){
		System.out.println("Creating guardian " + name + " ...");
		this.name = name;
		this.score = score;
		numOfGuardians++;
	}
	
	// method(s)
	
	void collectCoin() { 
		System.out.println("\nGuardian " + this.name + " collected coin!");
		this.score++; //as the problem domain specified
	}
	
	void shoot(Monster monster) {  // Association relationship with Monster; Monster is a parameter of this method
		
	 	System.out.println("\n" +this.name + " shoots a monster");
	 	// Every time the shoot method is called, a new bullet is created depending on the Guardians' bullet type
	 	this.bullet = new Bullet(this.bulletType); 

	 	this.bullet.viewState();

	 	// interaction of monster and guardian via bullet
	 	monster.health -= this.bullet.damage; // decrease monster's health by this Guardian's bullet damage

		
	}

	// let's create a class method
	static int count() {	 // can only access attributes (and even methods) that are static
		return numOfGuardians;
	}
	
	void viewState() {
		System.out.println("\nGuardian name: "+this.name);
		System.out.println("Guardian score: "+this.score); 
	}
	

	
}
