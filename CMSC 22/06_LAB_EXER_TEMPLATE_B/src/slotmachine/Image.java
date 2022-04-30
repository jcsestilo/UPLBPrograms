/**
 * @author Jan Coleen S. Estilo
 * Made on Oct. 29, 2021 at 0034.
 * Exercise 6
 */

package slotmachine;

public class Image {
	private String imageName;

	public final static String APPLE = "Apple";
	public final static String CHERRY = "Cherry";
	public final static String BANANA = "Banana";

	public final static String SKULL = "Skull";
	public final static String PIRATE_MATE = "Pirate mate";
	public final static String ANCHOR = "Anchor";
	public final static String TREASURE_MAP = "Treasure Map";
	public final static String GUN = "Gun";

	Image(String imageName){
		this.imageName = imageName;
	}

	String getImageName() {
		return this.imageName;
	}
}
