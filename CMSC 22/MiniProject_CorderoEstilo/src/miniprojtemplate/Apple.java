package miniprojtemplate;

import javafx.scene.image.Image;
import java.util.Random;

public class Apple extends Sprite {
	public static final int MAX_APPLE_SPEED = 5;
	public final static Image APPLE_IMAGE = new Image("images/apple.png",Apple.APPLE_WIDTH,Apple.APPLE_WIDTH,false,false);
	public final static int APPLE_WIDTH=50;
	private boolean alive;
	//attribute that will determine if a fish will initially move to the right
	private boolean moveRight;
	private int speed;


	Apple(int x, int y){
		super(x,y);
		this.alive = true;
		this.loadImage(Apple.APPLE_IMAGE);
		/*
		 *TODO: Randomize speed of fish and moveRight's initial value DONE
		 */
		Random r = new Random();
		this.speed = (r.nextInt(Apple.MAX_APPLE_SPEED)+1); // +1 because r can be 0, if r is 0, the fish will not move. So we add 1
													// so the the fish moves.

	}

	//method that changes the x position of the fish
	void move(){
		/*
		 * TODO: 				If moveRight is true and if the fish hasn't reached the right boundary yet,
		 *    						move the fish to the right by changing the x position of the fish depending on its speed
		 *    					else if it has reached the boundary, change the moveRight value / move to the left
		 * 					 Else, if moveRight is false and if the fish hasn't reached the left boundary yet,
		 * 	 						move the fish to the left by changing the x position of the fish depending on its speed.
		 * 						else if it has reached the boundary, change the moveRight value / move to the right
		 * DONE
		 */
		// May -50 sa window width kasi kapag walang -50, lumalampas yung mga apples sa boundary
		// kapag may - 50, hindi sila lumalampas
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
//
	public void setAlive(){
		this.alive = false;
	}
}
