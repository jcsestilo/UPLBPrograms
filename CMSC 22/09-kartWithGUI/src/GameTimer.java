/*************************************************************************************************************************
 *
 * CMSC 22 Object-Oriented Programming
 * Multithreading Example (with GUI): Kart
 * 
 * (c) Institute of Computer Science, CAS, UPLB
 * @author Miyah Queliste
 *
 *************************************************************************************************************************/
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;

public class GameTimer extends AnimationTimer {
	private GraphicsContext gc;
	private Kart myKart, yourKart;
	private Thread myKartThread, yourKartThread;
    
    public GameTimer(GraphicsContext gc) {
    	this.gc = gc;
    	
    	// These are the kart objects. We can add more in different positions.
    	this.myKart = new Kart(0, 100, "myKartThread");
    	this.yourKart = new Kart(0, 200, "yourKartThread");
		
    	// Since the karts are not threads but runnable objects, we create threads here (see Kart class - we implemented the runnable interface)
    	this.myKartThread = new Thread(myKart);
    	this.yourKartThread = new Thread(yourKart);	
    }
    
    public void startRace() {    	
    	this.myKartThread.start();
    	this.yourKartThread.start();
    }
    
    public void checkWinner() {
    	if(!this.myKartThread.isAlive() || !this.yourKartThread.isAlive()) {	
			this.stop();	// stop() is inherited from the AnimationTimer class
			
			try{
				this.myKartThread.join();
				this.yourKartThread.join();
			} catch(Exception e){}
			System.out.println("*****" + Kart.getWinner().getKartName() + " WON!*****");
    	}
    }
    
    // This method is overridden from the AnimationTimer class
	public void handle(long currentNanoTime) {
     
		// clear the canvas
        gc.clearRect(0, 0, Game.WINDOW_WIDTH,Game.WINDOW_HEIGHT);
        
        // render() is from the Sprite class which draws the kart images on the canvas
        this.myKart.render(gc);
        this.yourKart.render(gc);
        
        // for each step, we check if there's already a winner so we can stop the animation 
        this.checkWinner();
    }
}
