/**
 * @author Jan Coleen S. Estilo
 * Made on Oct. 29, 2021 at 0034.
 * Exercise 6
 */

package slotmachine;

import java.util.HashMap;
import java.util.Random;

public class FruitsSlotMachine extends DigitalSlotMachine {

	public final static int NO_OF_IMAGES = 3;

	public static final String[] BASE_IMAGES = {Image.APPLE, Image.CHERRY, Image.BANANA};


	/**
	 * TODO: Initialize needed attributes specific to FruitSlotMachine (1pt)
	 * Call method for initializing the reels
	 */
	public FruitsSlotMachine() {
		this.numberOfImages = NO_OF_IMAGES;
		this.baseImages = BASE_IMAGES;

		this.initReels();
	}

}
