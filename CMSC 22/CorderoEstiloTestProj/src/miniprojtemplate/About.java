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

public class About{

	private StackPane root;
	private Scene scene;

	public About(){
		this.root = new StackPane();
		this.scene = new Scene(root, GameStage.WINDOW_WIDTH, GameStage.WINDOW_HEIGHT);
	}

	public void setStageComponents (Stage stage){

		ImageView imgView = this.createView();
		VBox vertBox = this.createVBox(stage);

		this.root.getChildren().addAll(imgView, vertBox);

		stage.setTitle("About");
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

		Text txt1 = new Text("DEVELOPERS");
		Text txt2 = new Text("Alexandria Danielle DM. Cordero - 202002411");
		Text txt3 = new Text("is a 19 year old Taytayeno girl whose hobbies are playing");
		Text txt4 = new Text("valorant, watching series, and listening to music.");
		Text txt5 = new Text("Jan Coleen S. Estilo - 202005789");
		Text txt6 = new Text("is a 19 year old girl who lives in Binan Laguna. She likes");
		Text txt7 = new Text("eating fries and cooking fried rice with hotdogs and eggs.");

		Text txt8 = new Text("REFERENCES");
		Text txt9 = new Text("stackoverflow, CMSC 22 exer9 codes, CMSC 22 everwing codes");

		Button b1 = new Button("Back");

		this.addEventHandler(b1, stage);
		this.textTitle(txt1);
		this.textFill(txt2);
		this.textDesc(txt3);
		this.textDesc(txt4);
		this.textFill(txt5);
		this.textDesc(txt6);
		this.textDesc(txt7);
		this.textTitle(txt8);
		this.textFill(txt9);

		vbox.getChildren().addAll(txt1, txt2, txt3, txt4, txt5, txt6, txt7, txt8, txt9, b1);

		return vbox;
	}

//	sets that when the Back button is clicked, the screen is directed to the opener screen
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

	private void textDesc(Text txt){
		txt.setFill(Color.WHITE);
        txt.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20));
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
