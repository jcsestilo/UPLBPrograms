package example1template;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage stage) throws Exception {
		GameStage theGameStage = new GameStage();
		theGameStage.setStage(stage);
	}

}
