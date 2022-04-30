/*************************************************************************
 * 
 * Abstraction - Class part 1
 * Everwing Game example
 * Description:
 * The main method simulates the creation of guardians and collection of
 * points.
 * 
 * For every coin collected, 1 point is added to the score.
 * 
 * To monitor the state of the guardians, view state method is created and
 * called at certain points in the game.
 * 
 * We also need to monitor the number of guardians available in the game.
 * 
 * 
 * @author Mylah Rystie U. Anacleto
 * @date 2020-08-28 08:00
 *
 * (c) Institute of Computer Science, UPLB
 */
public class EverwingGame {

	public static void main(String[] args) {

		
		showMenu();
		
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

	}

	private static void showMenu() {
		// TODO Auto-generated method stub
		
	}

}
