/**
 * @author Jan Coleen S. Estilo
 * Made on Oct. 29, 2021 at 0034.
 * Exercise 6
 */

package slotmachine;

import java.util.HashMap;
import java.util.Random;

// This class will be similar to FruitsSlotMachine class
public class PirateSlotMachine extends DigitalSlotMachine{
	public final static int NO_OF_IMAGES = 5;

	public static final String[] BASE_IMAGES = {Image.SKULL, Image.PIRATE_MATE, Image.ANCHOR,
			Image.TREASURE_MAP, Image.GUN};


	/**
	 * TODO: Initialize needed attributes specific to PirateSlotMachine (1pt)
	 * Call method for initializing the reels
	 */
	public PirateSlotMachine() {
		this.numberOfImages = NO_OF_IMAGES;
		this.baseImages = BASE_IMAGES;

		this.initReels();
	}
}
