package example3;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class HelloWorld{
	private Scene scene;
	private StackPane root;
	
	public HelloWorld() {
		this.root = new StackPane();
		this.scene = new Scene(root, 500, 300);
	}

   
	public void setStageComponents(Stage stage) {
	
		// create the UI control node
		Button btn = new Button("Click me!");
		
		//convenience method event handler that will handle events
		btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				System.out.println("Hello World!");
			}
		});
		
		// add the created nodes/components to the layout
		this.root.getChildren().add(btn);
		
		// set the title and scene; display the components
		stage.setTitle("Hello World Application");
		stage.setScene(this.scene);
		stage.show();
	}
}
