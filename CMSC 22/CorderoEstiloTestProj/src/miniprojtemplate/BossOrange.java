package miniprojtemplate;

import java.util.Random;

import javafx.scene.image.Image;

// This class is similar to the Apples class, with some tweaks
public class BossOrange extends Sprite{
	public final static int ORANGE_WIDTH=100;
	public final static Image ORANGE_IMAGE = new Image("images/orange.png",BossOrange.ORANGE_WIDTH,BossOrange.ORANGE_WIDTH,false,false);
	public final static int MAX_ORANGE_SPEED = 5;

	private int health;
	private int speed;
	private boolean alive;
	private boolean moveRight;
	//private boolean moveLeft;
	// Constructor: similar to apple constructor
	public BossOrange(int x, int y){
		super(x,y);

		// The Boss Fish is initially alive
		this.alive = true;
		// load the image of the bossFish
		this.loadImage(BossOrange.ORANGE_IMAGE);

		// has a random movement speed between 1-5, inclusive,
		Random r = new Random();
		this.speed = (r.nextInt(BossOrange.MAX_ORANGE_SPEED)+1);

		// has a health of 3000.
		this.health = 3000;

	}
	//the -50 is there so that the apples will be confined within the boundary of the window
	void move(){
		// Same as in the move method in Apple
		if(this.moveRight){
			if(this.getX() < (GameStage.WINDOW_WIDTH)-50){
				this.x += this.speed;
			} else if(this.getX() >= (GameStage.WINDOW_WIDTH)-50){
				this.moveRight=false;
			}
		} else{
			if(this.getX() > 0){
				this.x -= this.speed;
			} else if(this.getX() <= 0){
				this.moveRight=true;
			}
		}
	}

	//getter
	public boolean isAlive() {
		return this.alive;
	}

	int getHealth(){
		return this.health;
	}


	// Setter
	void setHealth(int strength){
		this.health += strength;
		System.out.println(this.health);
	}

	void setAlive(){
		this.alive = false;
	}

}
