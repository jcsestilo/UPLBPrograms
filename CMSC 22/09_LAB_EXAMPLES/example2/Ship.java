package example2;

import java.util.Random;
import javafx.scene.image.Image;

public class Ship extends Sprite{
	private String name;
	private int strength;
	private boolean alive;
	
	public final static Image SHIP_IMAGE = new Image("images/ship.png",Ship.SHIP_WIDTH,Ship.SHIP_WIDTH,false,false);
	private final static int SHIP_WIDTH = 50;

	public Ship(String name, int x, int y){
		super(x,y);
		this.name = name;
		Random r = new Random();
		this.strength = r.nextInt(151)+100;
		this.alive = true;
		
		this.loadImage(Ship.SHIP_IMAGE);
	}
	//getters

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

	public void move() {
    	this.x += this.dx;
    	this.y += this.dy;
	}

}
