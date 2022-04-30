/*************************************************************************************************************************
 *
 * CMSC 22 Object-Oriented Programming
 * GUI Example: Hello World 
 *
 *************************************************************************************************************************/
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HelloWorld extends Application {
	
	public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root, 500, 200);
        
        Text text = new Text(200,50,"Hello World!");
        root.getChildren().add(text);
        
        stage.setScene(scene);
        stage.setTitle("GUI Example");
        stage.show();
    }
}
