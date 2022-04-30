package miniprojtemplate;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import miniprojtemplate.GameStage;
import miniprojtemplate.Instructions;

public class Opener {

	private StackPane root;
	private Scene scene;
	private AudioClip click = new AudioClip(getClass().getResource("/audio/click.mp3").toExternalForm());
	
	public Opener(){
		this.root = new StackPane();
		this.scene = new Scene(root, GameStage.WINDOW_WIDTH, GameStage.WINDOW_HEIGHT);
	}

	public void setStageComponents (Stage stage){

		ImageView imgView = this.createView();
		VBox vertBox = this.createVBox(stage);

		this.root.getChildren().addAll(imgView, vertBox);

		stage.setTitle("Mini Fruit Ninja");
		stage.setScene(this.scene);
		stage.show();
	}

//	sets the background of the window to splashscreen.png
	private ImageView createView(){
		Image bg = new Image("images/splashscreen.png");
		ImageView view = new ImageView();
		view.setImage(bg);

		return view;
	}

	private VBox createVBox(Stage stage){
		VBox vbox = new VBox();

		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(10));
		vbox.setSpacing(8);

		Button b1 = new Button("New Game");
		Button b2 = new Button("Instructions");
		Button b3 = new Button("About");

		this.buttonSize(b1);
		this.buttonSize(b2);
		this.buttonSize(b3);

		vbox.getChildren().addAll(b1, b2, b3);

//		sets that when the New Game button is clicked, the screen is directed to the game screen
		b1.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent e){
				click.play(); // play the audioclip for clicking
				
				GameStage theGameStage = new GameStage();
				theGameStage.setStage(stage);
			}
		});

//		sets that when the Instruction button is clicked, the screen is directed to the instruction screen
		b2.setOnMouseClicked(new EventHandler<MouseEvent>(){
			
			@Override
			public void handle(MouseEvent e){
				click.play(); // play the audioclip for clicking
				Instructions instruction = new Instructions();
				instruction.setStageComponents(stage);
			}
		});

//		sets that when the About button is clicked, the screen is directed to the about screen
		b3.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent e){
				click.play(); // play the audioclip for clicking
				About abt = new About();
				abt.setStageComponents(stage);
			}
		});

		return vbox;
	}

	private void buttonSize (Button btn){
		btn.setMaxWidth(150);
		btn.setMaxHeight(300);
		btn.setStyle("-fx-font-size: 10pt;");
	}

}
