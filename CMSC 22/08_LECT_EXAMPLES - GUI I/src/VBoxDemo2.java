/*************************************************************************************************************************
 *
 * CMSC 22 Object-Oriented Programming
 * GUI Example: VBox
 *
 *************************************************************************************************************************/
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

public class VBoxDemo2 extends Application {
	
	public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
    	
        StackPane root = new StackPane();
        
        // createView() is where we set up the ImageView for the image. It returns the ImageView object (see below)
        // createVBox() is where we set up the VBox containing the buttons. It returns the VBox object
        ImageView imgView = this.createView();
        VBox vertBox = this.createVBox();
        
        root.getChildren().addAll(imgView,vertBox);
        
        Scene scene = new Scene(root, 700, 400);
        stage.setScene(scene);
        stage.setTitle("GUI Example");
        stage.show();
    }
    
    private ImageView createView() {
        Image bg = new Image("pvz.gif");	// We cannot directly add an Image in the Scene 
        ImageView view = new ImageView();	// An ImageView can be used as leaf node in the Scene
        view.setImage(bg);
        
        return view;
    }
    
    private VBox createVBox() {
    	VBox vbox = new VBox();
    	
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);

        Button b1 = new Button("New Game");
        Button b2 = new Button("Load Game");
        
        vbox.getChildren().add(b1);
        vbox.getChildren().add(b2);
        
        this.setMouseHandler(b1);			// Instead of placing here the entire target.setOnMethodName(new EventHandler<EventType>(){}, 
        this.setMouseHandler(b2);			// we placed it in a method so we can reuse it
        
        return vbox;
    }
    
    private void setMouseHandler(Button b) {
    	b.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override 
            public void handle(MouseEvent e) {
                System.out.println(b.getText());
            }
        });
    }
}