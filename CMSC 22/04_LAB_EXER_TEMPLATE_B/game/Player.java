package game;
import java.util.Random;

class Player {
	
	// Instance attributes
	String 	name;
	int 	token;
	Maxie[] maxies;
	int 	maxieCount;

	// Constants
	final static int 	DEFAULT_TOKEN = 20;
	final static int 	MAX_MAXIE = 6;
	final static int 	BREEDING_COST = 3;

	/**
	 * Class Constructor
	 * @param name
	 */
	Player(String name) {
		this.name 	= name;
		this.token 	= Player.DEFAULT_TOKEN;
		this.maxies = new Maxie[Player.MAX_MAXIE];
	}
	
	/**
	 * Adds a maxie object to this player's array of maxies
	 * @param newMaxie
	 */
	void addMaxie(Maxie newMaxie) {
		this.maxies[this.maxieCount++] = newMaxie;
	}
	
	/**
	 * HINT: Returns the maxie object of this player in the provided index
	 * @param index
	 */
	Maxie getMaxie(int index) {
		return this.maxies[index];
	}
	
	/**
	 * Buy a new maxie
	 * Checks if this player can afford the price of the new maxie
	 * If yes, adds the new maxie to this player's array of maxies
	 * and deducts the maxie's price from this player's token
	 * 
	 * @param newMaxie
	 */
	void buyMaxie(Maxie newMaxie) {
		
		if(newMaxie != null) {
			if(this.token < newMaxie.price) {
				System.out.println("Not enough token.");
			} else {
				// Add the newMaxie to the player's maxies
				this.addMaxie(newMaxie);
				this.token -= newMaxie.price;
				System.out.println("--- " + this.name + " bought a " + newMaxie.type + " maxie for " + newMaxie.price + " tokens.");
			}	
		} else System.out.println("--- Maxie is undefined.");
	}
	
	/**
	 * Prints the state of this player
	 */
	void printState() {
		System.out.println("\n"+"=====================================");
		System.out.println(" Player's State:");
		System.out.println(" Name: "+this.name);
		System.out.println(" Token: "+this.token);
		System.out.println(" Maxie Count: "+this.maxieCount);
		System.out.println(" Maxie Collection: ");
		if(this.maxieCount==0) {
			System.out.println("\tNo maxies yet.");
		}else {
			for(int i=0;i<this.maxieCount;i++){
				System.out.println("\t[" +i+ "]");
				this.maxies[i].printState();
			}
		}
		System.out.println("=====================================");
		System.out.println();
		
	}
	
	/**
	 * Breeds two maxies of this player given two indices
	 * A new maxie object is created and added to this player's array of maxies
	 * 
	 * @param indexX, indexY
	 */
	void breed(int indexX, int indexY) {
		try {
			// If this player still has room for a new maxie
			if(this.maxieCount < Player.MAX_MAXIE) {
				
				// If this player can afford the breeding cost
				if(this.token >= Player.BREEDING_COST) {
				
					// If the parents are distinct maxies
					if(indexX != indexY) { // Check if maxie parents are distinct 
						Maxie maxieParentX = this.maxies[indexX];
						Maxie maxieParentY = this.maxies[indexY];
									
						// If the parents can still breed
						if(maxieParentX.canBreed() && maxieParentY.canBreed()) {
							System.out.println("--- Player is breeding "+maxieParentX.type+" maxie and "+maxieParentY.type+" maxie");
						
							maxieParentX.breedCount++;
							maxieParentY.breedCount++;
							
							Maxie babyMaxie;
							
							// Randomize what type of maxie the baby will be
							Random r = new Random();
							boolean genes = r.nextBoolean();
							if(genes) 	
								babyMaxie = new Maxie(maxieParentX.type);
							else 		
								babyMaxie = new Maxie(maxieParentY.type);
							
							System.out.println("--- A new maxie is born!");
							this.addMaxie(babyMaxie);
							babyMaxie.printState();
							
							this.printState();
							
							this.token -= Player.BREEDING_COST;
						
						} else System.out.println("--- Maxie has reached maximum breeding count. Breeding failed.");
					
					} else System.out.println("--- Maxie cannot breed on its own. Breeding failed.");
				
				} else System.out.println("--- Player doesn't have enough token. Breeding failed.");
				
			} else System.out.println("--- Maxie collection is full. Breeding failed.");
		
		}catch(Exception e) {
			// If indexX or indexY is beyond player's maxieCount, 
			// an ArrayIndexOutOfBounds or a NullPointerException will be thrown 
			// so we catch it here
			System.out.println("--- Maxie not found in player's collection. Breeding failed.");
		}
	}
}
