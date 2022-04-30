package example5template;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class GameStage {
	private Scene scene;
	private Stage stage;
	private Group root;
	private Canvas canvas;
	private GraphicsContext gc;
	private Image poro;
	private Image biscuit;
	private int poroXPos;
	
	public GameStage() {
		this.root = new Group();
		this.scene = new Scene(root, 400,400,Color.BLACK);				//create a 400x400 application with black background color	
		this.canvas = new Canvas(400,400);								//create a 400x400screen canvas for "drawing" elements
		this.gc = canvas.getGraphicsContext2D();
		this.poro = new Image("images/poro.png",120,120,false,false); 	// to resize the image upon loading
		this.biscuit = new Image("images/biscuit.png",50,50,false,false);
		this.poroXPos = 150;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
		
		//TODO: CALL the addComponents and handleKeyPressEvent() methods here
	
		
		//set stage elements here
		this.root.getChildren().add(canvas);
		this.stage.setTitle("The First Game Application");
		this.stage.setScene(this.scene);
		this.stage.show();
	}
	
	private void addComponents(){
		gc.clearRect(0, 0, 400, 400);
		
		Font theFont = Font.font("Times New Roman",FontWeight.BOLD,30);		//set font type, style and size
		this.gc.setFill(Color.PINK);										//set font color of text
		//gc.fillRect(0,0,100,100);
		this.gc.setFont(theFont);											
		 
		
		this.gc.fillText("Hello World!", 60, 50); 							//add a hello world to location x=60,y=50
		
		this.gc.setFill(Color.GREEN);										//set font color of text
		this.gc.fillText("First GUI Sample", 60, 120); 						//add the text to location x=60, y=120
		
		// Image poro = new Image("images/poro.png");						//to load the image; original size
		// gc.drawImage(poro, 150, 150);								
        
		//TODO: DRAW the images (poro and biscuit) to the canvas here
		
	}
	
	private void handleKeyPressEvent() {
		//TODO: ADD setOnKeyPress(); event handler method details here

    }
	
	private void setDX(int dx){
		//TODO: CHANGE x position of poro here and redraw the canvas components
	}
	
}
