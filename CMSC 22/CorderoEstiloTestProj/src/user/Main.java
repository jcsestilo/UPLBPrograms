package user;

import javafx.application.Application;
import javafx.stage.Stage;
import miniprojtemplate.GameStage;
import miniprojtemplate.Opener;
import miniprojtemplate.SplashScreen;

public class Main extends Application {

	public static void main(String[] args) {
//		int x;
//
//		SplashScreen frame = new SplashScreen();
//		frame.setStage(stage);
		
		launch(args);
	}

	public void start(Stage stage){
		SplashScreen frame = new SplashScreen();
		frame.setStageComponents(stage);
		
		try{
			Thread.sleep(3000);
		}catch(InterruptedException e){}
		
		Opener theOpener = new Opener();
		theOpener.setStageComponents(stage);
	}
}
