package game;

import java.util.Random;

public class Maxie {

	// Instance attributes
	private String 	type;
	private int 	life;
	private int 	damage;
	private int 	price;
	private int 	breedCount;
	private boolean alive;

	// Constants
	public final static String REPTILE = "REPTILE", 	BIRD = "BIRD", 	BUG = "BUG";
	public final static int 	REPTILE_PRICE = 6, 	BIRD_PRICE = 5, BUG_PRICE = 7;
	public final static int 	MIN_DMG = 7;
	public final static int 	MAX_DMG = 15;
	public final static int 	DEFAULT_LIFE = 25;
	public final static int 	MAX_BREED_COUNT = 3;

	/**
	 * Class Constructor
	 * @param type
	 */
	Maxie(String type) {
		Random r 	= new Random();
		this.type 	= type;
		this.damage = r.nextInt(Maxie.MAX_DMG-Maxie.MIN_DMG+1)+Maxie.MIN_DMG; // Randomizes maxie's damage (7-15)

		switch(type){
			case Maxie.REPTILE: 	this.price 	= Maxie.REPTILE_PRICE; break;
			case Maxie.BIRD: 	this.price 	= Maxie.BIRD_PRICE; break;
			case Maxie.BUG: 	this.price 	= Maxie.BUG_PRICE; break;
		}
		this.life 	= Maxie.DEFAULT_LIFE;
		this.alive 	= true;
	}

	/** =============== GETTERS AND SETTERS ===================
	 * MaxieInfinity will call the alive function and will check
	 * if the maxie is alive or dead. Will return boolean value
	 */
	boolean getAlive(){ // GETTER
		return this.alive;
	}

	/**
	 * MaxieInfinity & Player will call getPlayerMaxieType to get the type
	 * of the maxie
	 * @return String
	 */
	String getPlayerMaxieType(){ // GETTER
		return this.type;
	}

	/**
	 * Player will call this method to get the maxie price.
	 * @return int price
	 */
	int getMaxiePrice(){ // GETTER
		return this.price;
	}

	/**
	 * Player will call setBreedCount to change the value of the breed count
	 * of that particular maxie.
	 */
	void setBreedCount(){ // SETTER
		this.breedCount++;
	}

	/** ============================================================
	 * Attacks an opponent maxie
	 * Inflicted damage will vary (increase/decrease)
	 * depending on this maxie's type and the opponent's type
	 * Plant > Dawn > Beast > Plant
	 *
	 * @param opponent
	 */
	void attack(Maxie opponent) {

		float actualDamage = 0;

		if(this.type.equals(opponent.type)) {
			actualDamage = this.damage;
		}else if((this.type.equals(Maxie.REPTILE) && opponent.type.equals(Maxie.BIRD)) ||
			(this.type.equals(Maxie.BIRD) && opponent.type.equals(Maxie.BUG)) ||
				(this.type.equals(Maxie.BUG) && opponent.type.equals(Maxie.REPTILE))) {
			actualDamage = this.damage + (this.damage*0.15f);
		}else {
			actualDamage = this.damage - (this.damage*0.15f);
		}

		opponent.getHit(actualDamage);
		System.out.println("--- " + this.type + " maxie has inflicted " + actualDamage + " damage to "+ opponent.type +" maxie!");

	}

	/**
	 * Reduce this maxie's life with amount of damage
	 * Check if this maxie is dead
	 *
	 * @param damage
	 */
	private void getHit(float damage) {
		this.life -= damage;
		if(this.life <= 0) {
			this.die();
		}
	}

	/**
	 * Sets this maxie's alive to false
	 */
	private void die() {
		this.alive = false;
	}

	/**
	 * Reset this maxie's life
	 * Called after every battle
	 */
	void restore() {
		this.life 	= Maxie.DEFAULT_LIFE;
		this.alive 	= true;
	}

	/**
	 * Checks if this maxie's maximum breed count is not yet reached
	 */
	boolean canBreed() {
		if(this.breedCount<Maxie.MAX_BREED_COUNT) {
			return true;
		} else return false;
	}

	/**
	 * Prints the state of this maxie
	 */
	void printState() {
		System.out.println("\tType: "+this.type);
		System.out.println("\tDamage: "+this.damage);
		System.out.println("\tBreed count: "+this.breedCount+"/"+Maxie.MAX_BREED_COUNT);
		System.out.println();
	}
}
