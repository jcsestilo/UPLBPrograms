/**
 * @author Jan Coleen S. Estilo
 * Made on Oct. 29, 2021 at 0034.
 * Exercise 6
 */

package main;

import slotmachine.DigitalSlotMachine;
import slotmachine.PirateSlotMachine;
import slotmachine.FruitsSlotMachine;
import slotmachine.Player;

public class SlotMachineApp {

	public static void main(String[] args) {

		int round = 1;
		Player p = new Player();

		System.out.println("Player has " + p.getTokenOnHand() + " tokens.");

		while ((round <= 10 ) && (p.hasTokens()) ) {

			System.out.println("Round "+ round + " - use 1 token, reduced token to " + (p.getTokenOnHand()-1));

			DigitalSlotMachine m;

			/*
			 * TODO: Instantiate the theme needed. Themes will be alternating, starting with the FruitTheme. (1 pt)
			 */
			if (round % 2 == 0) {
				// PirateTheme
				// Cast m to PirateSlotMachine
				m = new PirateSlotMachine();
			} else {
				//FruitTheme
				// Cast m to FruitsSlotMachine
				m = new FruitsSlotMachine();
			}

			p.play(m);
			p.displayTokens();

			round++;

			System.out.println();

		}


	}

}
