package user;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import miniprojtemplate.GameStage;
import miniprojtemplate.Opener;
import miniprojtemplate.SplashScreen;

public class Main extends Application {

	public static void main(String[] args) {

		SplashScreen frame = new SplashScreen();
		// make the jframe centered
		frame.setLocationRelativeTo(null);
		// show the jframe
		frame.setVisible(true);

		try{
			Thread.sleep(3000);

			frame.dispose();
			launch(args);

		}catch(InterruptedException e){}
	}

	public void start(Stage stage){
		stage.setAlwaysOnTop(true);
		Opener theOpener = new Opener();
		theOpener.setStageComponents(stage);
	}
}
