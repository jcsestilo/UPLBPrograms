
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
	String name;
	int score;
	static int numOfGuardians = 0;
	
	// constructor(s)
	Guardian(String name){
		System.out.println("Creating guardian " + name + " ...");
		this.name = name;
		this.score = 0;
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
	
	// let's create a class method
	static int count() {	 // can only access attributes (and even methods) that are static
		return numOfGuardians;
	}
	
	void viewState() {
		System.out.println("\nGuardian name: "+this.name);
		System.out.println("Guardian score: "+this.score); 
	}
	

	
}
