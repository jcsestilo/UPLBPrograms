/**
 * @author Jan Coleen S. Estilo
 * Made on Oct. 29, 2021 at 0034.
 * Exercise 6
 */

package slotmachine;

public interface GenericSlotMachine {
	// automatic public and final
	String WINNER = "Winner";
	String GOOD_GAME = "Good game";
	String LOSER = "Loser";

	double GOOD_GAME_PERCENTAGE = 0.6d;

	void releaseToken(Player p);
	void pullLever(Player p);
	void stop();
}
