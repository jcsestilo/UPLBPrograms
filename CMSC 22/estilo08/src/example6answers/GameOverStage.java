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
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GameOverStage {
	private StackPane pane;
	private Scene scene;
	private GraphicsContext gc;
	private Canvas canvas;

	public final Image gameOverBG = new Image("images/gameover.png",500,500,false,true);
	public final Image youWinBG = new Image("images/youwin.png",500,500,false,true);

	GameOverStage(int winOrLose){


		this.pane = new StackPane();
		this.scene = new Scene(pane, GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT);
		this.canvas = new Canvas(GameStage.WINDOW_WIDTH, GameStage.WINDOW_HEIGHT);
		this.gc = canvas.getGraphicsContext2D();
		this.setProperties(winOrLose);

	}

	private void setProperties(int winOrLose){

		this.gc.setFill(Color.WHITE);										//set font color of text
		this.gc.fillRect(0,0,GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT);
		Font theFont = Font.font("Times New Roman",FontWeight.BOLD,30);//set font type, style and size
		this.gc.setFont(theFont);

		if(winOrLose==0){ // 0 - lose
			this.gc.drawImage(this.gameOverBG, 0, 0);
			//this.gc.setFill(Color.RED);										//set font color of text
			//this.gc.fillText("Game Over!", GameStage.WINDOW_WIDTH*0.3, GameStage.WINDOW_HEIGHT*0.3);						//add a hello world to location x=60,y=50

		} else { // else, win
			this.gc.drawImage(this.youWinBG, 0, 0);
			//this.gc.setFill(Color.GREEN);										//set font color of text
			//this.gc.fillText("Congratulations! You win!", GameStage.WINDOW_WIDTH*0.5, GameStage.WINDOW_HEIGHT*0.5);
		}

		Button exitbtn = new Button("Exit Game");
		this.addEventHandler(exitbtn);

		pane.getChildren().add(this.canvas);
		pane.getChildren().add(exitbtn);
	}

	private void addEventHandler(Button btn) {
		btn.setOnMouseClicked(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent arg0) {
				System.exit(0);
			}
		});

	}

	Scene getScene(){
		return this.scene;
	}

}
