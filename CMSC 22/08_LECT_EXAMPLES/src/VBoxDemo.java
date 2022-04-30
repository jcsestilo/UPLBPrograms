/*************************************************************************************************************************
 *
 * CMSC 22 Object-Oriented Programming
 * GUI Example: VBox
 *
 *************************************************************************************************************************/
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class VBoxDemo extends Application {
	
	public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
    	// Our root is a StackPane which will hold all the elements
    	StackPane root = new StackPane();

    	// In our StackPane is a Canvas. A Canvas is good for drawing images and shapes
        Canvas canvas = new Canvas(350,400);
        GraphicsContext gc = canvas.getGraphicsContext2D();	// To draw on a Canvas, we get its GraphicsContext
        
        Image bg = new Image("background.jpg");	
        gc.drawImage(bg, 0, 0);					// The image bg is drawn on the Canvas through its GraphicsContext. Origin at 0,0
        
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);

        Button b1 = new Button("New Game");
        Button b2 = new Button("Load Game");
        
        vbox.getChildren().add(b1);
        vbox.getChildren().add(b2);
        
        root.getChildren().addAll(canvas,vbox);
        
        Scene scene = new Scene(root, 350, 400);
        stage.setScene(scene);
        stage.setTitle("GUI Example");
        stage.show();
    }
}