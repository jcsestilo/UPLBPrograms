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

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Game {
	private Stage stage;
	private Scene splashScene;	// the splash scene
	private Scene scene;		// the game scene
	private Group root;
	private Canvas canvas;
	
	public final static int WINDOW_WIDTH = 400;
	public final static int WINDOW_HEIGHT = 700;
	
	public Game(){
		this.root = new Group();
		this.scene = new Scene( root );
		this.canvas = new Canvas( Game.WINDOW_WIDTH, Game.WINDOW_HEIGHT );
        this.root.getChildren().add( this.canvas );
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
		stage.setTitle( "Everwing" );
        
		this.initSplash(stage);			// initializes the Splash Screen with the New Game button
		
		stage.setScene( this.splashScene );
        stage.setResizable(false);
		stage.show();
	}
	
	private void initSplash(Stage stage) {
		StackPane root = new StackPane();
        root.getChildren().addAll(this.createCanvas(),this.createVBox());
        this.splashScene = new Scene(root);
	}
	
	private Canvas createCanvas() {
    	Canvas canvas = new Canvas(Game.WINDOW_WIDTH,Game.WINDOW_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        Image bg = new Image("images/background.jpg");
        gc.drawImage(bg, 0, 0);
        return canvas;
    }
    
    private VBox createVBox() {
    	VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);

        Button b1 = new Button("New Game");
        
        vbox.getChildren().add(b1);
        
        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override 
            public void handle(ActionEvent e) {
                setGame(stage);		// changes the scene into the game scene
            }
        });
        
        return vbox;
    }
	
	void setGame(Stage stage) {
        stage.setScene( this.scene );	
        
        GraphicsContext gc = this.canvas.getGraphicsContext2D();
        
        GameTimer gameTimer = new GameTimer(scene, gc);
        gameTimer.start();
        
	}	
}
