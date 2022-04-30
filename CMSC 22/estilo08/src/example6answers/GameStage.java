/**
 * @author Jan Coleen S. Estilo
 * Exercise 8: GUI I
 * Made Nov. 16, 2021 at 22:30
 *
 * Reference used:
 * https://www.geeksforgeeks.org/cpp-implementation-minesweeper-game/
 * GAME OVER BG: https://pngimg.com/image/83340
 * YOU WIN BG: http://pixelartmaker.com/art/49ec0299e7ada75
 */

package example6answers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameStage {
	private Scene scene;
	private Stage stage;
	/*Group layout/container is a component which applies no special
	layout to its children. All child components (nodes) are positioned at 0,0*/
	private Group root;
	private Canvas canvas;
	private GraphicsContext gc;
	private Element flag;
	private GridPane map;
	private int[][] gameBoard;
	private ArrayList<Element> landCells;

	private int flagCount; // made variable
	private int openedLandNotBomb;

	private boolean flagClicked=false;

	public final static int MAX_CELLS = 9;
	// Modify the GridPane. Make it 9 rows and 9 columns.
	public final static int MAP_NUM_ROWS = 9;
	public final static int MAP_NUM_COLS = 9;
	public final static int MAP_WIDTH = 400;
	public final static int MAP_HEIGHT = 400;
	public final static int CELL_WIDTH = 40;
	public final static int CELL_HEIGHT = 40;
	public final static int FLAG_WIDTH = 50;
	public final static int FLAG_HEIGHT = 50;
	public final static boolean IS_GAME_DONE = false;
	public final static int WINDOW_WIDTH = 500;
	public final static int WINDOW_HEIGHT = 500;
	// Attribute for maximum bombs
	public final static int MAX_BOMBS = 20;

	public final Image bg = new Image("images/background.jpg",500,500,false,false);

	public GameStage() {
		this.flagCount=0;
		this.openedLandNotBomb=0;

		this.root = new Group();
		this.scene = new Scene(root, GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT,Color.WHITE);
		this.canvas = new Canvas(GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT);
		this.gc = canvas.getGraphicsContext2D();
		this.flag = new Element(Element.FLAG_TYPE,this);
		this.map = new GridPane();
		this.landCells = new ArrayList<Element>();
		this.gameBoard = new int[GameStage.MAP_NUM_ROWS][GameStage.MAP_NUM_COLS];
	}

	//method to add the stage elements
	public void setStage(Stage stage) {
		this.stage = stage;
		//draw the background to the canvas at location x=0, y=70
		this.gc.drawImage( this.bg, 0, 0);

		this.initGameBoard();	//initialize game board with 1s and 0s
		this.createMap();

		//set stage elements here
		this.root.getChildren().add(canvas);
		this.root.getChildren().add(map);
		this.root.getChildren().add(this.flag.getImageView());
		this.stage.setTitle("Modified Minesweeper Game");
		this.stage.setScene(this.scene);
		this.stage.show();
	}

	private void initGameBoard(){
		Random r = new Random();

		int bomb = 1; // 1-bomb

		int bombCounter=0;

		while(bombCounter<GameStage.MAX_BOMBS){ // The board should have exactly 20 bombs

			// randomize a new row number and column number
			int row = r.nextInt(GameStage.MAP_NUM_ROWS);
			int col = r.nextInt(GameStage.MAP_NUM_COLS);

			// If a bomb is already placed in the randomized location/cell, randomize a new row and column
			if(this.gameBoard[row][col]==1) continue;
			else {
				// else, put the bomb(1) to randomized cell and increment bomb counter
				this.gameBoard[row][col] = bomb;
				bombCounter++;
			}

		}

		for(int i=0;i<GameStage.MAP_NUM_ROWS;i++){
			System.out.println(Arrays.toString(this.gameBoard[i]));//print final board content
		}
	}

	private void createMap(){
		//create 81 land cells
		for(int i=0;i<GameStage.MAP_NUM_ROWS;i++){
			for(int j=0;j<GameStage.MAP_NUM_COLS;j++){

				// Instantiate land elements
				Element land = new Element(Element.LAND_TYPE,this);
				land.initRowCol(i,j);

				//add each land to the array list landCells
				this.landCells.add(land);
			}
		}

		this.setGridPaneProperties();
		this.setGridPaneContents();
	}

	//method to set size and location of the grid pane
	private void setGridPaneProperties(){
		this.map.setPrefSize(GameStage.MAP_WIDTH, GameStage.MAP_HEIGHT);
		//set the map to x and y location; add border color to see the size of the gridpane/map
//	    this.map.setStyle("-fx-border-color: red ;");
		this.map.setLayoutX(GameStage.WINDOW_WIDTH*0.10);
	    this.map.setLayoutY(GameStage.WINDOW_WIDTH*0.10);
	    this.map.setVgap(5);
	    this.map.setHgap(5);
	}

	//method to add row and column constraints of the grid pane
	private void setGridPaneContents(){

		 //loop that will set the constraints of the elements in the grid pane
	     int counter=0;
	     for(int row=0;row<GameStage.MAP_NUM_ROWS;row++){
	    	 for(int col=0;col<GameStage.MAP_NUM_COLS;col++){
	    		 // map each land's constraints
	    		 GridPane.setConstraints(landCells.get(counter).getImageView(),col,row);
	    		 counter++;
	    	 }
	     }

	   //loop to add each land element to the gridpane/map
	     for(Element landCell: landCells){
	    	 this.map.getChildren().add(landCell.getImageView());
	     }
	}

	boolean isBomb(Element element){
		int i = element.getRow();
		int j = element.getCol();

		//if the row col cell value is equal to 1, cell has bomb
		if(this.gameBoard[i][j] == 1){
			System.out.println(">>>>>>>>>Bomb!");
			return true;
		}

		System.out.println(">>>>>>>>>SAFE!");
		return false;
	}

	public boolean isFlagClicked() {
		return this.flagClicked;
	}

	public void setFlagClicked(boolean value) {
		this.flagClicked = value;
	}

	Stage getStage() {
		return this.stage;
	}


	void flashGameOver(int winOrLose){
		PauseTransition transition = new PauseTransition(Duration.seconds(1));
		transition.play();

		transition.setOnFinished(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent arg0) {
				GameOverStage gameover = new GameOverStage(winOrLose);
				stage.setScene(gameover.getScene());
			}
		});
	}

	void incrementFlagCount(){
		this.flagCount++;
	}

	void decrementFlagCount(){
		this.flagCount--;
	}

	int getFlagCount(){
		return this.flagCount;
	}

	void incrementOpenedLandNotBomb(){
		this.openedLandNotBomb++;
	}

	int getOpenedLandNotBomb(){
		return this.openedLandNotBomb;
	}

	int[][] getGameBoard(){
		return this.gameBoard;
	}

}

