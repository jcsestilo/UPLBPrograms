/*************************************************************************
 * 
 * Abstraction - Class part 1
 * Everwing Game example
 * Description:
 * The main method simulates the creation of guardians, collection of
 * coins, and shooting of monster.
 * 
 * For every coin collected, 1 point is added to the score.
 * 
 * To monitor the state of the guardians, view state method is created and
 * called at certain points in the game.
 * 
 * We also need to monitor the number of guardians available in the game.
 * 
 *
 * (c) Institute of Computer Science, UPLB
 */
public class EverwingGame {

	public static void main(String[] args) {

		
		
		// Create a guardian object

		Guardian alice = new Guardian("alice");
		Guardian fiona = new Guardian("fiona", 50);
		System.out.println("We now have "+ Guardian.count()  +" guardians."); // can call the method even if there is not object
		// Through class attributes

		
		// Game play proper
		// Let's display the state of alice
		alice.viewState();   
		alice.collectCoin();
		alice.viewState();

		fiona.viewState();
		fiona.collectCoin();
		fiona.viewState();

		// Create a monster object

		Monster monster = new Monster();	// instantiate a Monster object; the type of Monster will be randomized in the constructor
		monster.viewState();
		
		alice.shoot(monster);		// call method shoot() on g1; pass the Monster to be shot: monster
		monster.viewState();
		
		
	}



}
