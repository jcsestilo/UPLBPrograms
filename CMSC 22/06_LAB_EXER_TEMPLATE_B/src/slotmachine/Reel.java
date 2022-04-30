/**
 * @author Jan Coleen S. Estilo
 * Made on Oct. 29, 2021 at 0034.
 * Exercise 6
 */

package slotmachine;

import java.util.ArrayList;
import java.util.Collections;

public class Reel {
	private ArrayList<Image> images;

	public final static String FRUIT = "Fruit";
	public final static String PIRATE = "Pirate";

	Reel(){
		this.images = new ArrayList<Image>();

	}

	void initImages(String[] images) {
		for(int i=0;i<images.length;i++) {
			Image img = new Image(images[i]);

			/*
			 * TODO: Add the image to the reel (1 pt)
			 */

			// add img to ArrayList images
			this.images.add(img);
		}
	}

	void roll() {
		/*
		 * TODO: Shuffle the images in the reel to simulate the spinning of the reels (1 pt)
		 */
		Collections.shuffle(this.images);
	}

	Image getImage(int payline) {
		/*
		 * TODO: Return the image in the payline of a particular reel (1 pt)
		 */
		// No need for payline-1 since Random class value between 0 (inclusive) and the specified value (exclusive)
		Image img = this.images.get(payline);
		return img;
	}
}
