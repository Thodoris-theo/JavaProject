
package project;

import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.util.List;


public class MoviesTableView {

    public void createMoviesTableView(Stage primaryStage) {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie(001, "Inception", 2010, List.of("Leonardo DiCaprio", "Joseph Gordon-Levitt"), 148));
        movies.add(new Movie(002, "The Matrix", 1999, List.of("Keanu Reeves", "Laurence Fishburne"), 136));
        movies.add(new Movie(003, "The Shawshank Redemption", 1994, List.of("Tim Robbins", "Morgan Freeman"), 142));
        TableView<Movie> movieTable = new TableView<>();

        TableColumn<Movie, Integer> codeCol = new TableColumn<>("Code");
        codeCol.setCellValueFactory(new PropertyValueFactory<>("code"));

        TableColumn<Movie, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Movie, Integer> yearCol = new TableColumn<>("Year");
        yearCol.setCellValueFactory(new PropertyValueFactory<>("year"));

        TableColumn<Movie, List<String>> actorsCol = new TableColumn<>("Actors");
        actorsCol.setCellValueFactory(new PropertyValueFactory<>("actors"));

        TableColumn<Movie, Integer> durationCol = new TableColumn<>("Duration");
        durationCol.setCellValueFactory(new PropertyValueFactory<>("duration"));

        movieTable.getColumns().addAll(codeCol, titleCol, yearCol, actorsCol, durationCol);
        movieTable.getItems().addAll(movies);

        Scene scene = new Scene(new BorderPane(movieTable), 900, 900);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setTitle("Movies");
        movieTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                TicketTable ticketMovie = new TicketTable();
                ticketMovie.showTicketsForMovie(newSelection,newSelection.getTitle());
            }
        });
    }




}
