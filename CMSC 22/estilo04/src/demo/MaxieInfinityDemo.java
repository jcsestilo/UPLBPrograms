/**
 * @author Jan Coleen Estilo
 * Made Oct. 20, 2021 at 21:00
 * Exercise 4
 */

package demo;

import game.*;

public class MaxieInfinityDemo {
	public static void main(String[] args){

		MaxieInfinity maxieInfinity = new MaxieInfinity();
		Player player1 = new Player("Juan");

		// Initially, player has no maxie
		player1.printState();

		// player1 buys a maxie created by the game
		Maxie newMaxie = maxieInfinity.makeRandomMaxie();
		player1.buyMaxie(newMaxie);

		newMaxie = maxieInfinity.makeRandomMaxie();
		player1.buyMaxie(newMaxie);

		// player1 have two maxies
		player1.printState();

		// player1 plays against the game's "AI"
		maxieInfinity.play(player1);
		player1.printState();

		maxieInfinity.play(player1);
		player1.printState();

		// player1 breeds first and second maxie
		player1.breed(0, 1);

		// player1 breeds first and third maxie
		player1.breed(0, 2);

		// player1 breeds first and second maxie
		player1.breed(0, 1);

		// player1 breeds first maxie for the 4th time (not allowed) assuming previous breeds succeeded
		player1.breed(0, 2);

		// player1 breeds second and seventh maxie (non-existent)
		player1.breed(1, 6);

		// This should not work
		// Maxie m = new Maxie(Maxie.BUG);

	}

}
