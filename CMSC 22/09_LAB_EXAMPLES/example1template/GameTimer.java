package example1template;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class GameTimer extends AnimationTimer{
	private GraphicsContext gc;
	private Scene scene;
	private long startSpawn;
	private Image poro;
	private Image biscuit;
	private int poroXPos;
	private double biscuitXPos;
	private double biscuitYPos;
	private Rectangle2D biscuitBounds;
	
	GameTimer(GraphicsContext gc, Scene scene){
		this.gc = gc;
		this.scene = scene;
		this.startSpawn = System.nanoTime();	//get current nanotime
		this.poro = new Image("images/poro.png",120,120,false,false);
		this.biscuit = new Image("images/biscuit.png",50,50,false,false);
		this.poroXPos = 150;
		this.biscuitXPos = 0;
		this.biscuitYPos = 150;
		this.handleKeyPressEvent();
		/*
		* 
		* SAMPLE 1. G - Which method below is for handling mouse events?
		*               Call the method.
		* 
		* Hint: similar to statement above (this.handleKeyPressEvent...)
		*
		* */    

	}
	@Override
	public void handle(long currentNanoTime) {
		this.addComponents();
		
		/*
		 * TODO SAMPLE 1. A - How do you print currentNanoTime in seconds?
		 * See JavaFX API 
		 * use java.util.concurrent.TimeUnit
		 * */
		//System.out.println(TimeUnit.       );
		
		/*
		 * TODO SAMPLE 1. B - Print the message "3 seconds done" after three seconds
		 * How?
		 *      Get the current time in sec
		 *      Get the start time in sec
		 *      if the difference is 3 (meaning three seconds has elapsed...), then print
		 *          the message "3 seconds done"
		 * */
//		long currentSec = TimeUnit.                     
//		long startSec = TimeUnit.                 
//		
//		if( ){
//			System.out.println("3 seconds done");
//		}
		
		/*
		 * TODO SAMPLE 1. C - Do something every 3 seconds
		 * Print the message "3 seconds done" every three seconds
		 * How?
		 *      Use the modulo operator
		 *      set the start spawn time to new time
		 * */
//		long currentSec = TimeUnit.
//		long startSec = TimeUnit.	
//		if(      ){
//			System.out.println("3 seconds done");
//			this.startSpawn =  
//		}
		
		/*
		 * TODO SAMPLE 1. D - Move the biscuit from left to right of the window screen
		 * How?
		 *       In the If statement below, check if biscuitXPos is less than
		 *            or equal to GameStage.WINDOW_WIDTH-50 (not yet out of bounds)
		 *       If not yet out of bounds, increment biscuitXPos by 2 units
		 *       Then, move the biscuit using this.moveBiscuit(...) method
		 * */
//		if(    ){
//			this.biscuitXPos
//		}
//		
//		this.moveBiscuit(...)
		
		/*
		 * TODO SAMPLE 1.E-The biscuit appears in random x and y location every 3 sec
		 * */

	}//end of handle
	
	private void moveBiscuit(double x, double y) {
        //draw the biscuit image at location x and y 
        gc.drawImage( this.biscuit, x, y );	
	}

	private void addComponents(){
		this.gc.clearRect(0, 0, 400, 400);
        this.gc.drawImage(poro, this.poroXPos, 150); 
		/*
		 * TODO SAMPLE 1. F - Add biscuit bounds for the mouse event listener
		 * */
		//get size of the biscuit
		
		//add bound/rectangle to the biscuit
		
		//System.out.println("BISCUIT BOUNDS: "+this.biscuitBounds.toString());    		
	}

	private void handleKeyPressEvent() {
		this.scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
				public void handle(KeyEvent e){
					if (e.getCode() == KeyCode.RIGHT) {
						System.out.println("Right key pressed!");
						setDX(10);
				    } else if (e.getCode() == KeyCode.LEFT) {
				    	      System.out.println("Left key pressed!");
				    	      setDX(-10);
				    }
                              addComponents();

	            }
	        });
}
	
	private void handleMouseEvent() {
		this.scene.setOnMouseClicked(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e){
				if ( biscuitBounds.contains( e.getX(), e.getY() ) ){
					System.out.println("Biscuit clicked!");
				}
			}
		});
    }

	private void setDX(int dx){
		this.poroXPos += dx;
	}
}

