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

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Element {
	private String type;
	protected Image img;
	protected ImageView imgView;
	protected GameStage gameStage;
	protected int row, col;

	public final static Image FLAG_IMAGE = new Image("images/flag.png",GameStage.CELL_WIDTH,GameStage.CELL_WIDTH,false,true);
	public final static Image BOMB_IMAGE = new Image("images/bomb.gif",GameStage.CELL_WIDTH,GameStage.CELL_WIDTH,false,false);
	public final static Image LAND_IMAGE = new Image("images/land.png",GameStage.CELL_WIDTH,GameStage.CELL_WIDTH,false,false);
	public final static int IMAGE_SIZE = 70;

	// NEIGHBORING BOMBS ATTRIBUTES
	public final static Image ONE_NEIGHBOR = new Image("images/one.png",GameStage.CELL_WIDTH,GameStage.CELL_WIDTH,false,false);
	public final static Image TWO_NEIGHBOR = new Image("images/two.png",GameStage.CELL_WIDTH,GameStage.CELL_WIDTH,false,false);
	public final static Image THREE_NEIGHBOR = new Image("images/three.png",GameStage.CELL_WIDTH,GameStage.CELL_WIDTH,false,false);
	public final static Image FOUR_NEIGHBOR = new Image("images/four.png",GameStage.CELL_WIDTH,GameStage.CELL_WIDTH,false,false);
	public final static Image FIVE_NEIGHBOR = new Image("images/five.png",GameStage.CELL_WIDTH,GameStage.CELL_WIDTH,false,false);
	public final static Image SIX_NEIGHBOR = new Image("images/six.png",GameStage.CELL_WIDTH,GameStage.CELL_WIDTH,false,false);
	public final static Image SEVEN_NEIGHBOR = new Image("images/seven.png",GameStage.CELL_WIDTH,GameStage.CELL_WIDTH,false,false);
	public final static Image EIGHT_NEIGHBOR = new Image("images/eight.png",GameStage.CELL_WIDTH,GameStage.CELL_WIDTH,false,false);

	public final static String FLAG_TYPE = "flag";
	public final static String BOMB_TYPE = "bomb";
	public final static String LAND_TYPE = "land";
	public final static String LAND_FLAG_TYPE = "landToFlag";

	public Element(String type, GameStage gameStage) {
		this.type = type;
		this.gameStage = gameStage;

		// load image depending on the type
		switch(this.type) {
			case Element.FLAG_TYPE: this.img = Element.FLAG_IMAGE; break;
			case Element.LAND_TYPE: this.img = Element.LAND_IMAGE; break;
			case Element.BOMB_TYPE: this.img = Element.BOMB_IMAGE; break;
		}

		this.setImageView();
		this.setMouseHandler();
	}

	protected void loadImage(String filename,int width, int height){
		try{
			this.img = new Image(filename,width,height,false,false);
		} catch(Exception e){}
	}


	String getType(){
		return this.type;
	}

	int getRow() {
		return this.row;
	}

	int getCol() {
		return this.col;
	}


	protected ImageView getImageView(){
		return this.imgView;
	}

	void setType(String type){
		this.type = type;
	}

	void initRowCol(int i, int j) {
		this.row = i;
		this.col = j;
	}

	private void setImageView() {
		// initialize the image view of this element
		this.imgView = new ImageView();
		this.imgView.setImage(this.img);
		this.imgView.setLayoutX(0);
		this.imgView.setLayoutY(0);
		this.imgView.setPreserveRatio(true);

		if(this.type.equals(Element.FLAG_TYPE)) {
			this.imgView.setFitWidth(GameStage.FLAG_WIDTH);
			this.imgView.setFitHeight(GameStage.FLAG_HEIGHT);
		}else {
			this.imgView.setFitWidth(GameStage.CELL_WIDTH);
			this.imgView.setFitHeight(GameStage.CELL_HEIGHT);
		}
	}

	private void setMouseHandler(){
		Element element = this;
		this.imgView.setOnMouseClicked(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e) {
                switch(element.getType()) {
	                case Element.FLAG_TYPE: 		// if flag, set flagClicked to true
								                	System.out.println("FLAG clicked!");
								    	            gameStage.setFlagClicked(true);
								    	            break;
	    			case Element.LAND_TYPE:
			    									System.out.println("LAND clicked!");
			    									// If land is clicked, but flag not click
								    				if(!gameStage.isFlagClicked()) {
								    					if(!gameStage.isBomb(element)){	// if not a bomb, clear image
								    						clearImage(element);

								    						gameStage.incrementOpenedLandNotBomb(); // If the player opens a land without a bomb,
								    						// increment this counter. This is for the winning condition

								    						// will check if there are neighboring cells with bombs
								    						int neighboringBombs = checkNeighbors(element, gameStage);

								    						// Change the cell’s image depending on the number of bombs using setImage() method.
								    						switch(neighboringBombs){
									    						case 1:
									    							changeImage(element, Element.ONE_NEIGHBOR);
									    							break;
									    						case 2:
									    							changeImage(element, Element.TWO_NEIGHBOR);
									    							break;
									    						case 3:
									    							changeImage(element, Element.THREE_NEIGHBOR);
									    							break;
									    						case 4:
									    							changeImage(element, Element.FOUR_NEIGHBOR);
									    							break;
									    						case 5:
									    							changeImage(element, Element.FIVE_NEIGHBOR);
									    							break;
									    						case 6:
									    							changeImage(element, Element.SIX_NEIGHBOR);
									    							break;
									    						case 7:
									    							changeImage(element, Element.SEVEN_NEIGHBOR);
									    							break;
									    						case 8:
									    							changeImage(element, Element.EIGHT_NEIGHBOR);
									    							break;
									    						default: // if 0, clearImage is already done
									    							break;

								    						}
								    					}
								    					else {
								    						changeImage(element,Element.BOMB_IMAGE); // if bomb, change image to bomb
								    						gameStage.flashGameOver(0);
								    					}
								    	            } else {// flag is clicked
								    	            	changeImage(element,Element.FLAG_IMAGE);	// if flag was clicked before hand, change image to flag
								    	            	element.setType(LAND_FLAG_TYPE);			// change type to landToFlag
								    	            	gameStage.setFlagClicked(false);	    	// reset flagClicked to false

								    	            	// If a flag is placed to a block/land with a bomb, increase the flag_count by one.
								    	            	if(gameStage.isBomb(element)){
								    	            		gameStage.incrementFlagCount();
								    	            	}

								    	            }
								    				break;
	    			case Element.LAND_FLAG_TYPE:
								    				changeImage(element,Element.LAND_IMAGE);		// if flag is clicked, change image back to land
							    	            	element.setType(LAND_TYPE);
							    	            	gameStage.decrementFlagCount(); // decrement flagCount
							    	            	break;
                }

                // The whole game board minus the total bombs is the number of cells with no bombs. If these are equal, to be used in the winning condition below
                // In this case, the equation is (9*9)=81, 81-20=61 cells need to be opened
                boolean openedAllLandsNoBombs = (GameStage.MAP_NUM_COLS*GameStage.MAP_NUM_ROWS)-GameStage.MAX_BOMBS==gameStage.getOpenedLandNotBomb();

                // The user wins if all the blocks / lands with bombs are marked with flags or when the user successfully opens
                // all blocks / lands without bombs
                if((gameStage.getFlagCount()==GameStage.MAX_BOMBS) || openedAllLandsNoBombs){
                	gameStage.flashGameOver(1);
                }

			}	//end of handle()
		});
	}

	private void clearImage(Element element) {
		imgView.setImage(null);
	}

	private void changeImage(Element element, Image image) {
		this.imgView.setImage(image);

	}

	// To check if that certain cell is within the map
	boolean isValid(int row, int col){
		if((row>=0 && row<GameStage.MAP_NUM_ROWS) && (col>=0 && col<GameStage.MAP_NUM_COLS)){
			return true;
		} else return false;
	}

	private int checkNeighbors(Element element, GameStage gamestage){
		// getting the gameboard so we can access the neighboring cells
		int[][] gameBoard = gamestage.getGameBoard();
		int rowOfElement = element.getRow();
		int colOfElement = element.getCol();

		int neighborBombsCounter=0;

		/*
		 *  Cell-->Current Cell (row, col)
	        N -->  North        (row-1, col)
	        S -->  South        (row+1, col)
	        E -->  East         (row, col+1)
	        W -->  West         (row, col-1)
	        N.E--> North-East   (row-1, col+1)
	        N.W--> North-West   (row-1, col-1)
	        S.E--> South-East   (row+1, col+1)
	        S.W--> South-West   (row+1, col-1)
		 */

		// North
		if(isValid(rowOfElement-1,colOfElement) && gameBoard[rowOfElement-1][colOfElement]==1) neighborBombsCounter++;
		// South
		if(isValid(rowOfElement+1,colOfElement) && gameBoard[rowOfElement+1][colOfElement]==1) neighborBombsCounter++;
		// East
		if(isValid(rowOfElement,colOfElement+1) && gameBoard[rowOfElement][colOfElement+1]==1) neighborBombsCounter++;
		// West
		if(isValid(rowOfElement,colOfElement-1) && gameBoard[rowOfElement][colOfElement-1]==1) neighborBombsCounter++;
		// North-East
		if(isValid(rowOfElement-1,colOfElement+1) && gameBoard[rowOfElement-1][colOfElement+1]==1) neighborBombsCounter++;
		// North-West
		if(isValid(rowOfElement-1,colOfElement-1) && gameBoard[rowOfElement-1][colOfElement-1]==1) neighborBombsCounter++;
		// South-East
		if(isValid(rowOfElement+1,colOfElement+1) && gameBoard[rowOfElement+1][colOfElement+1]==1) neighborBombsCounter++;
		// South-West
		if(isValid(rowOfElement+1,colOfElement-1) && gameBoard[rowOfElement+1][colOfElement-1]==1) neighborBombsCounter++;

		// once we are done getting the amount of neighboring bombs, return neighboring bombs
		return neighborBombsCounter;


	}
}
