/*************************************************************************************************************************
 *
 * CMSC 22 Object-Oriented Programming
 * GUI Example: GridPane
 *
 *************************************************************************************************************************/
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class GridPaneDemo extends Application {
    public static void main(String[] args) 
    {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        // Our root is a GridPane which will hold all the elements
    	GridPane root = new GridPane();
        
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setVgap(5);
        root.setHgap(5);
        root.setAlignment(Pos.CENTER);
        
        // These are the leaf nodes that we will place in our GridPane
        Label label1 = new Label("Username:");
        TextField textField = new TextField();
        Label label2 = new Label("Password:");
        PasswordField passField = new PasswordField();
        Button submit = new Button("Submit");
        
        // Here, we position the elements on the GridPane by specifying (x,y) 
        GridPane.setConstraints(label1, 0, 0);
        GridPane.setConstraints(textField, 1, 0);
        GridPane.setConstraints(label2, 0, 1);
        GridPane.setConstraints(passField, 1, 1);
        GridPane.setConstraints(submit, 1, 2);
        
        submit.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        
        // Don't forget to add the elements to the root. Shortcut method here is addAll()
        root.getChildren().addAll(label1,textField,label2,passField,submit);
        
        Scene scene = new Scene(root, 300, 150);
        stage.setScene( scene );
        stage.setTitle( "GUI Example" );
        stage.show();
    }
}