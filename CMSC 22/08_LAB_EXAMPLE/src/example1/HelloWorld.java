package example1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class HelloWorld extends Application {

	public static void main(String[] args) {
		launch(args);
	}

    //override start() method; stage components and details can be added here
	public void start(Stage stage) {
	
		// create the UI control node
		Button btn = new Button("Click me!");
		
		// instantiate the desired layout class
		StackPane root = new StackPane();
		
		// add the created nodes/components to the layout
		root.getChildren().add(btn);
		
		// instantiate the Scene
		Scene scene = new Scene(root, 500, 300);
		
		// set the title and scene; display the components
		stage.setTitle("Hello World Application");
		stage.setScene(scene);
		stage.show();
	}
}
