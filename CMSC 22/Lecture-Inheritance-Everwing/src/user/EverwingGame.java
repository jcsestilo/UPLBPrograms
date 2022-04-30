package user;
import everwing.Clover;
import everwing.Coin;
import everwing.Gem;
import everwing.Guardian;
import everwing.Monster;

/*************************************************************************
 * 
 * Topic on OOP Inheritance
 * Everwing Game example
 * Description:
 * The main method simulates the creation of guardians, collection of
 * items (e.g. coins, gems, clover)
 * 
 * - For every coin collected, 1 point is added to the score. 
 * - Purple Gem adds +10 score.
 * - Clover increase the player's bullet level  (upgrade)
 * 
 * To monitor the state of the guardians, view state method is created and
 * called at certain points in the game.
 * 
 * We also need to monitor the number of guardians available in the game.
 * 
 * 
 * @date 2020-09-01 22:00
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


//		fiona.viewState();
//		fiona.collectCoin();
//		fiona.viewState();
		
		// collect items
		
		Coin coin = new Coin();
		Gem gem = new Gem();
		Clover clover = new Clover();
		
		alice.collectCoin(coin); //once coin is collected, should be marked as used
		alice.viewState();

		alice.collectGem(gem);
		
		alice.collectClover(clover);
		
		// Create a monster object

		Monster monster = new Monster();	// instantiate a Monster object; the type of Monster will be randomized in the constructor
		monster.viewState();
		
		alice.shoot(monster);		// call method shoot() on g1; pass the Monster to be shot: monster
		monster.viewState();
		
		
	}



}
