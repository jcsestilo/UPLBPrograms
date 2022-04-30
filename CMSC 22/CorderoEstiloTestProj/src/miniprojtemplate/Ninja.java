package miniprojtemplate;

import java.util.ArrayList;
import java.util.Random;
import javafx.scene.image.Image;



public class Ninja extends Sprite implements Runnable{
	private String name;
	private int strength;
	private boolean alive;
	private boolean immortal;

	private ArrayList<Sword> swords;
	public final static Image NINJA_IMAGE = new Image("images/ninja.png",Ninja.NINJA_WIDTH,Ninja.NINJA_WIDTH,false,false);
	private final static int NINJA_WIDTH = 50;

	public Ninja(String name, int x, int y){
		super(x,y);
		this.name = name;
		Random r = new Random();
		this.strength = r.nextInt(51)+100;
		this.alive = true;
		this.swords = new ArrayList<Sword>();
		this.loadImage(Ninja.NINJA_IMAGE);

		this.immortal = false;
	}
	@Override
	public void run(){
		try{
			Thread.sleep(3000);
		}catch(InterruptedException e){}
		this.setImmortal(false);
		return;
	}

	Thread getNinjaAsThread(){
		return (new Thread(this));
	}

	public boolean isAlive(){
		if(this.alive) return true;
		return false;
	}
	public String getName(){
		return this.name;
	}

	public void die(){
    	this.alive = false;
    }

	//method that will get the bullets 'shot' by the ship
	public ArrayList<Sword> getSwords(){
		return this.swords;
	}

	//method called if spacebar is pressed
	public void shoot(){
		//compute for the x and y initial position of the bullet
		int x = (int) (this.x + this.width+20);
		int y = (int) (this.y + this.height/2);

		Sword sword = new Sword(x, y);
		this.swords.add(sword);
    }

	//method called if up/down/left/right arrow key is pressed.
	public void move() {

		if(this.x > 0 && this.x < (GameStage.WINDOW_WIDTH - 50)){
			this.x += this.dx;
		} if(this.y > 0 && this.y < (GameStage.WINDOW_HEIGHT - 50)){
			this.y += this.dy;
		}

		// If the ninja is in boundary and the input is trying to get INSIDE the boundary

		// If x is at leftmost and dx is to the right OR if x is rightmost and dx is to the left
		if((this.x <= 0 && this.dx > 0) || (this.x > (GameStage.WINDOW_WIDTH - 50) && this.dx < 0)){
			this.x += this.dx;
		} if((this.y <= 0 && this.dy > 0) || (this.y > (GameStage.WINDOW_HEIGHT - 50) && this.dy < 0)){
			this.y += this.dy;
		}
	}

	public int getNinjaStrength(){
		if(this.strength < 0){
			this.strength = 0;
		}

		return this.strength;
	}

	public void setNinjaStrength(int number){
		this.strength += number;
	}

	boolean isImmortal(){
		return this.immortal;
	}

	void setImmortal(boolean value){
		this.immortal = value;
		System.out.println("Immortal Status of Ninja: " + this.immortal);
	}


}
