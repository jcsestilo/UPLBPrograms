/*************************************************************************************************************************
 *
 * CMSC 22 Object-Oriented Programming
 * Multithreading Example (with GUI): Kart
 * 
 * (c) Institute of Computer Science, CAS, UPLB
 * @author Miyah Queliste
 *
 *************************************************************************************************************************/
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class Game {

	private Scene scene;
	private Group root;
	private Canvas canvas;
	
	public final static int WINDOW_WIDTH = 568;
	public final static int WINDOW_HEIGHT = 568;
	
	Game(){
		// We set up the structure of the window. We'll use a canvas to animate our sprites
		this.root = new Group();
		this.scene = new Scene( root );
		this.canvas = new Canvas( Game.WINDOW_WIDTH, Game.WINDOW_HEIGHT );
        this.root.getChildren().add( this.canvas );
	}
	
	void setStage(Stage stage) {
		stage.setTitle( "Kart" );
        stage.setScene( this.scene );
        
        GraphicsContext gc = this.canvas.getGraphicsContext2D();
        
        // GameTimer is the class that extends Java's AnimationTimer. This is where the kart race takes place.
        // We pass the gc object so we can draw on the canvas
        GameTimer gameTimer = new GameTimer(gc);
        gameTimer.start();		// This starts the gameTimer. Once started, handle() is called in every frame.
        
        stage.show();
        gameTimer.startRace();	// We start our kart threads to start racing. We do this after showing the stage.
        
	}	
}
