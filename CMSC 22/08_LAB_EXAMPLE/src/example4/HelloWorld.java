package example4;

import javafx.scene.Scene;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HelloWorld {
	
	// attributes
	private Scene scene;
	private VBox root;
	
	public HelloWorld() {
		this.root = new VBox();
		this.scene = new Scene(root, 500, 300);
	}
	
	public void setStageComponents(Stage stage) {
		
		// widgets
		Button clickme = new Button("Click me!");
		Button exit = new Button("Exit");
		
		exit.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				handleButtonClick("exit");
			}
		});
		
		clickme.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				handleButtonClick("clickme");
			}
		});
		
		// set stage elements
		this.root.getChildren().add(clickme);
		this.root.getChildren().add(exit);
		
		stage.setTitle("Hello World Application");
		stage.setScene(this.scene);
		stage.show();
	}
	
	private void handleButtonClick(String btnName) {
		if(btnName.contentEquals("clickme")) {
			System.out.println("Hello World!");
		} else {
			System.out.println("End of Program! Bye!");
			System.exit(0);
		}
	}
	
	
}
