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

public class GridPaneDemo2 extends Application {
    public static void main(String[] args) 
    {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        GridPane root = new GridPane();
        
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setVgap(5);
        root.setHgap(5);
        root.setAlignment(Pos.CENTER);
        
        Label label1 = new Label("Username:");
        TextField textField = new TextField();
        Label label2 = new Label("Password:");
        PasswordField passField = new PasswordField();
        Button submit = new Button("Submit");
        
        GridPane.setConstraints(label1, 0, 0);
        GridPane.setConstraints(textField, 1, 0);
        GridPane.setConstraints(label2, 0, 1);
        GridPane.setConstraints(passField, 1, 1);
        GridPane.setConstraints(submit, 1, 2);
        
        submit.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        
        root.getChildren().addAll(label1,textField,label2,passField,submit);
        
        // Format: target.setOnMethodName(new EventHandler<EventType>(){}
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override 
            public void handle(ActionEvent e) {
                if(textField.getText().equals("") || passField.getText().equals(""))
                	System.out.println("Please fill in username and password.");
                else
                	System.out.println("Congratulations! You're logged in!");
            }
        });
        
        Scene scene = new Scene(root, 350, 150);
        stage.setScene( scene );
        stage.setTitle( "GUI Example" );
        stage.show();
    }
}