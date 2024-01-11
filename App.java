
/*
 Spyros Kanakopoylos 3212018071 
   Thodoris Theodorellis 3212018061
 */
package project;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {


        launch(args);

    }

    @Override
    public void start(Stage primaryStage) {
        MoviesTableView moviesTableView = new MoviesTableView(); 
        PartyTableView partyTableView = new PartyTableView();
        
        Button button1 = new Button("Party");
        Button button2 = new Button("Movie");
        
        
        button1.setOnAction(e ->  partyTableView.createPartyTableView(primaryStage));
        button2.setOnAction(e ->  moviesTableView.createMoviesTableView(primaryStage));

        HBox buttonBox = new HBox(10); 
        buttonBox.getChildren().addAll(button1, button2);
        buttonBox.setAlignment(Pos.CENTER);

        StackPane layout = new StackPane();
        layout.getChildren().add(buttonBox);

        Scene scene = new Scene(layout, 300, 200); 

        primaryStage.setScene(scene);

        primaryStage.setTitle("Two Buttons Scene");

        primaryStage.show();
    }
}
