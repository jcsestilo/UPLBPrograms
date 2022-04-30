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

import javafx.scene.image.Image;

class Guardian extends Sprite {
	private String name;
	private boolean alive;
	private int bulletType;
	private int score;
	private ArrayList<Bullet> bullets;
	private BulletTimer timer;

	private final static Image GUARDIAN_IMAGE = new Image("images/guardian.png");
	private final static double INITIAL_X = 150;
	private final static double INITIAL_Y = 590;

	Guardian(String name){
       	super(Guardian.INITIAL_X, Guardian.INITIAL_Y, Guardian.GUARDIAN_IMAGE);
		this.name = name;
		this.alive = true;
		this.bullets = new ArrayList<Bullet>(); 
	}

	String getName(){
		return this.name;
	}

	int getScore(){
		return this.score;
	}

	ArrayList<Bullet> getBullets(){
		return this.bullets;
	}
	
	void shoot(){
        this.bullets.add(new Bullet(this.bulletType,this.xPos+(this.width/2),this.yPos));
    }

    void die(){
    	this.alive = false;
    }
    
    private boolean isUpgraded() {
    	if(this.bulletType == Bullet.UPGRADED_BULLET)
    		return true;
    	else return false;
    }

    void downgradeBullets(){
    	this.bulletType = Bullet.ORDINARY_BULLET;
    }   

    void upgradeBullets(){
    	if(!this.isUpgraded()){
	    	this.bulletType = Bullet.UPGRADED_BULLET;
	    	this.timer = new BulletTimer(this);
	    	this.timer.start();
	    }else this.timer.refresh();
    }

    void gainScore(int increase){
    	this.score+=increase;
    	System.out.println("Score: "+score);
    }

    boolean isAlive(){
    	return this.alive;
    } 

    void move() {
    	if(this.xPos+this.dx >= 0 && this.xPos+this.dx <= Game.WINDOW_WIDTH-this.width)
			this.xPos += this.dx;
	}
}