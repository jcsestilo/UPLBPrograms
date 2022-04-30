/**
 * @author Jan Coleen S. Estilo
 * Made on Oct. 29, 2021 at 0034.
 * Exercise 6
 */

package slotmachine;

public class Player {

	private int tokenOnHand = 4;

	public int getTokenOnHand() {
		return this.tokenOnHand;
	}

	public boolean hasTokens() {
		if (tokenOnHand > 0) return true;
		else {
			System.out.println("Sorry, no more tokens left!");
			return false;
		}
	}

	private void reduceTokens() {
		tokenOnHand--;
	}

	public void play(DigitalSlotMachine slotMachine) {
		this.reduceTokens();

		/*
		 * TODO: Call the method that will that will start the round (1 pt)
		 * Think: the player starts the digital slot machine by pulling the lever to make the reels start spinning
		 */

		slotMachine.pullLever(this); // Call pullLever method in class DigitalSlotMachine. Will pass the player object
	}

	void getReward(int rewardTokens) {
		this.tokenOnHand += rewardTokens;
	}

	public void displayTokens() {
		System.out.println("Tokens on hand: " + tokenOnHand);
	}
}
