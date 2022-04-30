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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import miniprojtemplate.GameStage;

public class Instructions{

	private StackPane root;
	private Scene scene;

	public Instructions(){
		this.root = new StackPane();
		this.scene = new Scene(root, GameStage.WINDOW_WIDTH, GameStage.WINDOW_HEIGHT);
	}

	public void setStageComponents (Stage stage){

		ImageView imgView = this.createView();
		VBox vertBox = this.createVBox(stage);

		this.root.getChildren().addAll(imgView, vertBox);

		stage.setTitle("Instructions");
		stage.setScene(this.scene);
		stage.show();
	}

//	sets the background of the window to splashscreen.png
	private ImageView createView(){
		Image bg = new Image("images/background.png");
		ImageView view = new ImageView();
		view.setImage(bg);
		view.setFitHeight(GameStage.WINDOW_HEIGHT);
		view.setFitWidth(GameStage.WINDOW_WIDTH);

		return view;
	}

	private VBox createVBox(Stage stage){
		VBox vbox = new VBox();

		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(10));
		vbox.setSpacing(8);

		Text txt1 = new Text("INSTRUCTIONS");
		Text txt2 = new Text("Arrow keys (Up, Down, Left, Right) - move the ninja");
		Text txt3 = new Text("Spacebar - shoot bullets");
		Text txt4 = new Text("OBJECTIVE");
		Text txt5 = new Text("Keep the ninja alive for a minute by shooting the obstacles ahead.");
		Button b1 = new Button("Back");

		this.addEventHandler(b1, stage);
		this.textTitle(txt1);
		this.textTitle(txt4);
		this.textFill(txt2);
		this.textFill(txt3);
		this.textFill(txt5);

		vbox.getChildren().addAll(txt4, txt5, txt1, txt2, txt3, b1);

		return vbox;
	}

//	sets that when the Back button is clicked, the screen is directed to the opener screen as well
//	as changes the size of the button
	private void addEventHandler(Button btn, Stage stage) {

		btn.setMaxWidth(150);
		btn.setMaxHeight(300);
		btn.setStyle("-fx-font-size: 10pt;");

		btn.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e){
				Opener theOpener = new Opener();
				theOpener.setStageComponents(stage);
			}
		});

	}

	private void textTitle(Text txt){
		txt.setFill(Color.WHITE);
        txt.setStroke(Color.BLACK);
        txt.setStrokeWidth(1);
        txt.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 50));
	}

	private void textFill(Text txt){
		txt.setFill(Color.WHITE);
        txt.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 25));

	}
}
