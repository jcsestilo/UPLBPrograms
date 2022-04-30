/*************************************************************************************************************************
 *
 * CMSC 22 Object-Oriented Programming
 * Everwing Example
 * Problem Domain: Everwing is a game where the goal is for the guardian to kill monsters by shooting them and collect coins.  
 * When a guardian is created, it is given a name and initially a score of 0. The guardian can move to the left or to the right. 
 * Monsters appear by waves and die when health reaches 0. 
 * When a monster dies, it becomes a coin or item that the guardian can collect. There is the red and the green type monster 
 * with 10 and 50 health respectively.
 * When the guardian hits a monster, the guardian dies.
 * Different items can be collected by the guardian:
 * Item 		Effect
 * Coin 		Adds +1 score and 1 coin
 * Purple Gem 	Adds +10 score and 10 coins
 * Clover 		Increase the player's bullet level by 5 for the 5 seconds
 * 
 * (c) Institute of Computer Science, CAS, UPLB
 * @author Miyah Queliste
 * @date 2018-05-10 
 *************************************************************************************************************************/
package everwing;
import java.util.ArrayList;
import java.util.Random;

import javafx.scene.image.Image;

class Monster extends Sprite {
	private int health;
	private ArrayList<Item> collectibles;
	private final static double MONSTER_SPEED = 3;
	private final static Image RED_MONSTER_IMAGE = new Image("images/red.png");
	private final static Image GREEN_MONSTER_IMAGE = new Image("images/green.png");
	private final static int RED_MONSTER_HEALTH = 10;
	private final static int GREEN_MONSTER_HEALTH = 50;


	Monster(int type, int x, int y, ArrayList<Item> collectibles){
		super(x, y, type==0?Monster.RED_MONSTER_IMAGE:Monster.GREEN_MONSTER_IMAGE);
		this.health = type==0?Monster.RED_MONSTER_HEALTH:Monster.GREEN_MONSTER_HEALTH;
		this.collectibles = collectibles;
	}

	void move() {
		this.yPos += Monster.MONSTER_SPEED;
		if(this.yPos >= Game.WINDOW_HEIGHT){	// if this monster passes through the bottom of the scene, set visible to false
			this.vanish();
		}
	}

	private void getHit(int damage){
		this.health-=damage;
		if(this.health<=0){
			this.die();
		}
	}

	/*
     * If a monster dies, it gets converted into an item/collectible
     * Randomize whether monster becomes a coin (most common), gem, or clover
     * */
	private void die(){
		int type;
		Item newCollectible;
		Random r = new Random();

		type = r.nextInt(30);
		switch(type){
			case 10: newCollectible = new Gem(this.xPos,this.yPos); break;
			case 20: newCollectible = new Clover(this.xPos,this.yPos); break;
			default: newCollectible = new Coin(this.xPos,this.yPos); break;
			
		} 		
		this.collectibles.add(newCollectible);
		this.vanish();
	}

	/*
     * Traverses through all the bullets of guardian
     * If hit, damage is applied and the bullet vanishes
     * Checks if this monster collides with the guardian itself -> guardian dies
     * */
	void checkCollision(Guardian guardian){
		for	(int i = 0; i < guardian.getBullets().size(); i++)	{
			if (this.collidesWith(guardian.getBullets().get(i))){
				this.getHit(guardian.getBullets().get(i).getDamage());
				guardian.getBullets().get(i).vanish();
			}
		}
		if(this.collidesWith(guardian)){
			guardian.die();		
		}
	}
}