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

import javafx.scene.image.Image;

class Bullet extends Sprite{
	private int type;
	private int damage;
	
	private final static double BULLET_SPEED = 10;
	private final static int ORDINARY_BULLET_DAMAGE = 10;
	private final static int UPGRADED_BULLET_DAMAGE = 25;
	private final static Image ORDINARY_BULLET_IMAGE = new Image("images/bullet.png");
	private final static Image UPGRADED_BULLET_IMAGE = new Image("images/fire.png");

	public final static int ORDINARY_BULLET = 0;
	public final static int UPGRADED_BULLET = 1;


	Bullet(int type, double x, double y){
		super(x,y,type==Bullet.ORDINARY_BULLET?Bullet.ORDINARY_BULLET_IMAGE: Bullet.UPGRADED_BULLET_IMAGE);
		this.type = type;
		this.damage = this.type==Bullet.ORDINARY_BULLET?Bullet.ORDINARY_BULLET_DAMAGE:Bullet.UPGRADED_BULLET_DAMAGE;
	}

    int getDamage(){
    	return this.damage;
    }

	void move(){
		this.yPos -= Bullet.BULLET_SPEED;
		if(this.yPos <= 0){				// if this bullet passes through the top of the scene, set visible to false
			this.vanish();
		}
	}
}
