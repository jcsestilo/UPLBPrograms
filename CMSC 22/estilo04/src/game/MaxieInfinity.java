package game;

import java.util.Random;

public class MaxieInfinity {

	// Instance attribute
	private Player aiPlayer;

	// Constants
	public final static int WIN_TOKENS = 2;
	public final static int LOSE_TOKENS = 1;

	/**
	 * Class Constructor
	 */
	public MaxieInfinity() {
		this.aiPlayer = new Player("AI");
		this.initAI();
	}

	/**
	 * Initializes the maxies of the AI player
	 */
	private void initAI() {
		// Instantiate random maxies for the AI player
		for(int i=0;i<Player.MAX_MAXIE;i++) {
			this.aiPlayer.addMaxie(this.makeRandomMaxie());
		}
	}

	/**
	 * Instantiates a random type of maxie
	 * Called so a player can buy the randomly created maxie
	 */
	public Maxie makeRandomMaxie() {
		Random r = new Random();
		int x = r.nextInt(3)+1;
		Maxie newMaxie = null;

		// Instantiate a random maxie
		switch(x){
			case 1: newMaxie = new Maxie(Maxie.REPTILE); break;
			case 2: newMaxie = new Maxie(Maxie.BIRD); break;
			case 3: newMaxie = new Maxie(Maxie.BUG); break;
		}

		return newMaxie;
	}

	/**
	 * player fights this game's AI player
	 * Checks if player has at least one maxie
	 * Randomly selects a maxie from the player's array and the ai's array
	 * Randomizes which maxie attacks first
	 * Maxies alternately attack each other until one dies
	 * Checks whether the player wins
	 * Restores the life of the maxies
	 *
	 * @param player
	 */
	public void play(Player player) {

		if (player != null) {
			if (player.getMaxieCount() != 0) {

				Maxie playerMaxie = this.randomSelectMaxie(player);
				Maxie aiPlayerMaxie = this.randomSelectMaxie(aiPlayer);

				System.out.println("--- "+player.getPlayerName()+"'s " + playerMaxie.getPlayerMaxieType() + " maxie was selected for battle!");
				playerMaxie.printState();
				System.out.println("--- AI's " + aiPlayerMaxie.getPlayerMaxieType() + " maxie was selected for battle!");
				aiPlayerMaxie.printState();

				System.out.println("\n>>> START! \n");

				// Randomize which maxie attacks first
				Maxie maxieA = playerMaxie, maxieB = aiPlayerMaxie;
				this.selectFirstAttacker(maxieA, maxieB);

				while(maxieA.getAlive() && maxieB.getAlive()) {

					maxieA.attack(maxieB);
					if(maxieB.getAlive())
						maxieB.attack(maxieA);
				}

				this.checkResult(player, playerMaxie);	// check if the player wins

				playerMaxie.restore();
				aiPlayerMaxie.restore();

			} else {
				System.out.println("--- No maxies yet. Cannot play.");

			}
		} else System.out.println("--- Player undefined. ");
	}

	/**
	 * Returns the randomly selected maxie from the playerX's array
	 *
	 * @param playerX
	 */
	private Maxie randomSelectMaxie(Player playerX) {
		Random r = new Random();

		int x = r.nextInt(playerX.getMaxieCount());	// random index for maxie array

		Maxie randomMaxie = playerX.getMaxie(x);
		return randomMaxie;
	}

	/**
	 * Randomizes which maxie is set as maxieA (first to attack) and maxieB (second)
	 *
	 * @param maxieA, maxieB
	 */
	private void selectFirstAttacker(Maxie maxieA, Maxie maxieB) {
		Random r = new Random();

		boolean swap = r.nextBoolean();
		if(swap) {
			Maxie temp = maxieA;
			maxieA = maxieB;
			maxieB = temp;
		}
	}

	/**
	 * Checks the result of the game
	 * Updates player's token depending on the result
	 *
	 * @param player, playerMaxie
	 */
	private void checkResult(Player player, Maxie playerMaxie) {
		if(playerMaxie.getAlive()) {
			System.out.println("--- YOU WON!");
			System.out.println("--- Gained "+ MaxieInfinity.WIN_TOKENS +" token/s");
			//player.token += MaxieInfinity.WIN_TOKENS;
			player.setPlayerToken(true);
		}else {
			System.out.println("--- YOU LOST!");
			System.out.println("--- Lost "+ MaxieInfinity.LOSE_TOKENS +" token/s");
			//player.token -= MaxieInfinity.LOSE_TOKENS;
			player.setPlayerToken(false);
		}
	}

}
