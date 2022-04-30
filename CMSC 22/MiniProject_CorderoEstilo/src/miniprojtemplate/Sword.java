package miniprojtemplate;

import java.util.ArrayList;
import javafx.scene.image.Image;

public class Sword extends Sprite {
	private final int SWORD_SPEED = 20;
	public final static Image SWORD_IMAGE = new Image("images/sword.png",Sword.SWORD_WIDTH,Sword.SWORD_WIDTH,false,false);
	public final static int SWORD_WIDTH = 20;
	//private ArrayList<Apple> apples;

	public Sword(int x, int y){
		super(x,y);
		//this.apples = new ArrayList<Apple>();
		this.loadImage(Sword.SWORD_IMAGE);
	}


	//method that will move/change the x position of the bullet
	public void move(){
		/*
		 * TODO: Change the x position of the bullet depending on the bullet speed.
		 * 					If the x position has reached the right boundary of the screen,
		 * 						set the bullet's visibility to false.
		 */
		// Change the x position of the bullet depending on the bullet speed.
		this.x = this.getX() + this.SWORD_SPEED;
		// If the x position has reached the right boundary of the screen,
		// 						set the bullet's visibility to false.
		if(this.x >= GameStage.WINDOW_WIDTH){
			this.setVisible(false);
		}
	}

//	void addFish(Fish fish){
//		this.fishes.add(fish);
//	}
//
//	void hitFish(){
//
//	}
}