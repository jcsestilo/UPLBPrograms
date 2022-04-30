/**
 * @author Jan Coleen S. Estilo
 * Made on Oct. 29, 2021 at 0034.
 * Exercise 6
 */

package slotmachine;

import java.util.HashMap;
import java.util.Random;

/*
 * TODO: Apply the proper encapsulation to the attributes/methods that need them. (2 pts)
 */

public abstract class DigitalSlotMachine implements GenericSlotMachine {

	protected int reelsCnt;

	protected Reel[] reels;

	protected HashMap<String,Integer> imageFrequencyMapping;
	protected Image[] paylineImages;

	protected String result;
	protected int numberOfImages;

	protected int winnerTokens;
	protected int goodGameTokens;

	protected String[] baseImages;

	public static final int MAX_REELS = 7;
	public static final int MIN_REELS = 5;

	protected DigitalSlotMachine(){
		Random r = new Random();

		while(true) {
			if(this.reelsCnt>=DigitalSlotMachine.MIN_REELS && this.reelsCnt<=DigitalSlotMachine.MAX_REELS) {
				break;
			}

			this.reelsCnt = r.nextInt(DigitalSlotMachine.MAX_REELS+1);
		}

		this.imageFrequencyMapping = new HashMap<String,Integer>();
		this.paylineImages = new Image[this.reelsCnt];
		this.reels = new Reel[this.reelsCnt];

	}

	/**
	 * initReels() - create reel objects and populate the reels' images depending on the theme
	 */
	protected void initReels() {
		for(int i=0; i<this.reelsCnt; i++) {
			this.reels[i] = new Reel();

			this.reels[i].initImages(this.baseImages);
		}
	}

	/**
	 * pullLever(Player p) - simulates the pulling of the lever of a player.
	 * The all the reels will roll/spin, and then stop.
	 * The tokens will be released as a reward to the player.
	 */
	@Override
	public void pullLever(Player p) {
		for (int i = 0; i<reelsCnt; i++) {
			this.reels[i].roll();
		}
		this.stop();
		this.releaseToken(p);
	}

	/**
	 * stop() - simulates stopping/pulling down of lever of slot machine
	 * The payline is the random number i from each array list in each slot.
	 */
	public void stop() {
		Random r = new Random();
		int payline = r.nextInt(this.numberOfImages);


		// Gets the images in the payline of each reel and saves it to another array.
		int cnt = 0;
		for (int j = 0; j<reelsCnt; j++) {

			Image k = this.reels[j].getImage(payline);

			this.paylineImages[cnt] = k;
			cnt++;

		}

		this.compareSlots(payline);
		this.displayResult();
	}

	/**
	 * compareSlots(int index) - counts the occurrence of each image and stores them in a hashmap
	 */
	private void compareSlots(int index) {
		this.determineImageFrequency();

		/*
		 * TODO: Declare the result as a win if all the images in the payline is the same (1 pt)
		 */
		// IF the hashmap contains a value equal to the number of reels
		if(this.imageFrequencyMapping.containsValue(this.reelsCnt)){
			// PLAYER WINS
			this.result = WINNER;
		}
		else {determineResult();}
	}

	/**
	 * TODO: Get the frequency of each image (2 pts)
	 * Sample hashMap contents:
	 * apple, 1
	 * banana, 2
	 * cherry, 1
	 */
	private void determineImageFrequency() {
		for(int i = 0; i< this.paylineImages.length; i++) {
			// Getting the particular image
			Image img = this.paylineImages[i];

			/* IF the img is already inside the HashMap imageFrequencyMapping, then increment the
			* value by 1
			* Since the key is string, we will get the Image Name (String)
			**/
			if(this.imageFrequencyMapping.containsKey(img.getImageName())){
				// Put method because it replaces the previous element in the HashMap
				this.imageFrequencyMapping.put(img.getImageName(), (this.imageFrequencyMapping.get(img.getImageName())+1));
			} else{ // ELSE put img in the hashmap with 1 as value
				this.imageFrequencyMapping.put(img.getImageName(), 1);
			}
		}
	}

	/**
	 * determineResult () - based on the hashMap of image frequencies, determine the result.
	 * This method determines the image in the payline that has the highest occurrence and determines if the outcome is
	 * 		a good game or a loser
	 */
	private void determineResult() {
		int largest = -1;
		String keyWithLargest = "";

		// for each key in lahat ng keys ng imageFrequencyMapping
		for (String key: this.imageFrequencyMapping.keySet()) {
			int freq = imageFrequencyMapping.get(key);

			if (largest == -1) {
				largest = freq;
				keyWithLargest = key;
			} else {
				if (freq > largest) {
					largest = freq;
					keyWithLargest = key;
				} else if (freq == largest) {
					String newStr = keyWithLargest + ", " + key;
					keyWithLargest = newStr;
				}
			}
		}

		double frac = (double)largest/(double)reelsCnt;
		System.out.println(keyWithLargest + " - " + largest + "/" + reelsCnt + " images");

		if (frac >= GenericSlotMachine.GOOD_GAME_PERCENTAGE) this.result = GenericSlotMachine.GOOD_GAME;
		else this.result = GenericSlotMachine.LOSER;

	}

	/**
	 * displayResult() - Displays the event/result (win,lose,good game)
	 */
	private void displayResult() {
		this.displayPaylineImages();
		System.out.println("Result: " + this.result);
	}

	/**
	 * displayPaylineImages() - displays the payline images (text)
	 */
	private void displayPaylineImages() {
		for(int i = 0; i < reelsCnt; i++) {
			Image img = this.paylineImages[i];
			System.out.print("[" + img.getImageName() +"]  ");
		}
		System.out.println();
	}

	/**
	 * TODO: Depending on the result of the round, release the appropriate amount of tokens to the player (1 pt)
	 */
	public void releaseToken(Player p) {
		int rewardTokens = 0;

		// If the result is WINNER, the player gains 4 tokens
		if(this.result.equals(WINNER)) {rewardTokens = 4;}
		// If the result is GOOD_GAME, the player gains 2 tokens
		else if(this.result.equals(GOOD_GAME)) {rewardTokens = 2;}
		// If the result is LOSER, the player gains 0 tokens. Since rewardTokens is already 0, no need to do anything
		// else {}

		// Reward the tokens to the player
		p.getReward(rewardTokens);
	}

}
