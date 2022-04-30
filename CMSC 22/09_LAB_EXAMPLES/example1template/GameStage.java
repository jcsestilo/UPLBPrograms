package example1template;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameStage {
	private Scene scene;
	private Stage stage;
	private Group root;
	private Canvas canvas;
	private GraphicsContext gc;
	private GameTimer gametimer; 
	public final static int WINDOW_WIDTH = 400;
	public final static int WINDOW_HEIGHT = 400;
	
	public GameStage() {
		this.root = new Group();
		this.scene = new Scene(root, GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT,Color.BLACK);		//create a 400x400 application with black background color	
		this.canvas = new Canvas(GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT);							//create a 400x400screen canvas for "drawing" elements
		this.gc = canvas.getGraphicsContext2D();
		this.gametimer = new GameTimer(this.gc,this.scene);
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	
		this.gametimer.start();
		
		//set stage elements here
		this.root.getChildren().add(canvas);
		this.stage.setTitle("Example 1");
		this.stage.setScene(this.scene);
		this.stage.show();
	}	
}
