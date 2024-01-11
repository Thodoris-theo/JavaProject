/*
 Spyros Kanakopoylos 3212018071 
   Thodoris Theodorellis 3212018061
 */
package project;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TicketTable extends MoviesTableView {

    private ObservableList<String> ticketTypes = FXCollections.observableArrayList("A", "B", "C");
    private ArrayList<Ticket> tickets = new ArrayList<>();
    private TableView<Ticket> ticketsTable = new TableView<>();

    public void showTicketsForMovie(Movie selectedMovie, String movieName) {
        Stage ticketsStage = new Stage();

        TableColumn<Ticket, String> ticketCodeCol = new TableColumn<>("Ticket Code");
        ticketCodeCol.setCellValueFactory(new PropertyValueFactory<>("ticketCode"));

        TableColumn<Ticket, String> seatNumberCol = new TableColumn<>("Seat Number");
        seatNumberCol.setCellValueFactory(new PropertyValueFactory<>("seat"));

        TableColumn<Ticket, String> DateColumn = new TableColumn<>("Date ");
        DateColumn.setCellValueFactory(new PropertyValueFactory<>("issueDate"));

        TableColumn<Ticket, Double> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        ticketsTable.getColumns().addAll(ticketCodeCol, seatNumberCol, DateColumn, priceCol);

        ObservableList<Ticket> ticketData = FXCollections.observableArrayList(generateTickets(selectedMovie));
        ticketsTable.setItems(ticketData);

        BorderPane borderPane = new BorderPane(ticketsTable);
        Scene ticketsScene = new Scene(borderPane, 400, 300);

        ticketsStage.setScene(ticketsScene);
        ticketsStage.setTitle(movieName);
        ticketsStage.show();

        Button addButton = new Button("Add");
        addButton.setPrefWidth(100);
        addButton.setPrefHeight(40);

        Button editButton = new Button("Edit");
        editButton.setPrefWidth(100);
        editButton.setPrefHeight(40);

        Button deleteButton = new Button("Delete");
        deleteButton.setPrefWidth(100);
        deleteButton.setPrefHeight(40);

        VBox buttonBox = new VBox(10);
        buttonBox.getChildren().addAll(addButton, editButton, deleteButton);

        BorderPane root = new BorderPane();
        root.setCenter(ticketsTable);
        root.setRight(buttonBox);

        int paddingValue = 20;
        int paddingValueBox = 30;

        root.setPadding(new Insets(paddingValue, paddingValue, paddingValue, paddingValue));
        BorderPane.setMargin(buttonBox, new Insets(150, 0, 0, paddingValueBox));

        Scene buttons_scene = new Scene(root, 800, 600);
        ticketsStage.setTitle("Tickets of " + selectedMovie.getTitle());
        ticketsStage.setScene(buttons_scene);
        ticketsStage.show();
        root.setRight(buttonBox);

        addButton.setOnAction(event -> {addTicket(selectedMovie, ticketData);});
        deleteButton.setOnAction(event -> deleteSelectedTicket(ticketData));
        editButton.setOnAction(event -> editSelectedTicket());

    }

    private List<Ticket> generateTickets(Movie movie) {
        //Example 

        Date issueDate = new Date();
        tickets.add(new Ticket( movie, issueDate, "A", "A12", 15.0));
        tickets.add(new Ticket( movie, issueDate, "B", "B05", 12.0));
        tickets.add(new Ticket( movie, issueDate, "C", "C08", 10.0));
        return tickets;
    }

    private void addTicket(Movie selectedMovie, ObservableList<Ticket> ticketData) {
        Stage addTicketStage = new Stage();
        addTicketStage.setTitle("Add Ticket");

        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));

        
        Label seatNumberLabel = new Label("Seat Number:");
        Label priceLabel = new Label("Price:");
        Label ticketTypeLabel = new Label("Ticket Type:");

       
        TextField seatNumberTextField = new TextField();
        TextField priceTextField = new TextField();

        ChoiceBox<String> ticketTypeChoiceBox = new ChoiceBox<>(ticketTypes);

        Button saveButton = new Button("Save");
        saveButton.setOnAction(saveEvent -> {
            if (validateFields( seatNumberTextField.getText(), priceTextField.getText())) {
                Ticket newTicket = new Ticket(
                        selectedMovie,
                        new Date(),
                        ticketTypeChoiceBox.getValue(),
                        seatNumberTextField.getText(),
                        Double.parseDouble(priceTextField.getText())
                );

                ticketData.add(newTicket);
                addTicketStage.close();
            } else {
                displayErrorAlert("Invalid input. Please check the fields.");
            }
        });
        gridPane.add(seatNumberLabel, 0, 1);
        gridPane.add(seatNumberTextField, 1, 1);
        gridPane.add(priceLabel, 0, 2);
        gridPane.add(priceTextField, 1, 2);
        gridPane.add(ticketTypeLabel, 0, 3);
        gridPane.add(ticketTypeChoiceBox, 1, 3);
        gridPane.add(saveButton, 1, 4);
        Scene addTicketScene = new Scene(gridPane, 500, 250);
        addTicketStage.setScene(addTicketScene);
        addTicketStage.show();
    }

    private boolean validateFields( String seatNumber, String price) {
        return  !seatNumber.isEmpty() || price.isEmpty();
    }

    private void deleteSelectedTicket(ObservableList<Ticket> ticketData) {
        Ticket selectedTicket = ticketsTable.getSelectionModel().getSelectedItem();
        if (selectedTicket != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Deletion");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete this ticket?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                ticketData.remove(selectedTicket);
                ticketsTable.refresh();
            }
        } else {
            displayErrorAlert("Please select a ticket to delete.");
        }
    }

    private void displayErrorAlert(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(errorMessage);

        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().setAll(okButton);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == okButton) {
            //
        }
    }

    private void editSelectedTicket() {
        Ticket selectedTicket = ticketsTable.getSelectionModel().getSelectedItem();
        if (selectedTicket != null) {
            Stage editTicketStage = new Stage();
            editTicketStage.setTitle("Edit Ticket");

            GridPane gridPane = new GridPane();
            gridPane.setVgap(10);
            gridPane.setHgap(10);
            gridPane.setPadding(new Insets(20, 150, 10, 10));

            
            Label seatNumberLabel = new Label("Seat Number:");
            Label priceLabel = new Label("Price:");
            Label ticketTypeLabel = new Label("Ticket Type:");

            TextField seatNumberTextField = new TextField(selectedTicket.getSeat());
            TextField priceTextField = new TextField(String.valueOf(selectedTicket.getPrice()));

            ChoiceBox<String> ticketTypeChoiceBox = new ChoiceBox<>(ticketTypes);
            ticketTypeChoiceBox.setValue(selectedTicket.getTicketType());

            Button saveButton = new Button("Save");
            saveButton.setOnAction(saveEvent -> {
                if (validateFields( seatNumberTextField.getText(), priceTextField.getText())) {
                  
                    selectedTicket.setSeat(seatNumberTextField.getText());
                    selectedTicket.setPrice(Double.parseDouble(priceTextField.getText()));
                    selectedTicket.setTicketType(ticketTypeChoiceBox.getValue());

                    ticketsTable.refresh();
                    editTicketStage.close();
                } else {
                    displayErrorAlert("Invalid input. Please check the fields.");
                }
            });
            gridPane.add(seatNumberLabel, 0, 1);
            gridPane.add(seatNumberTextField, 1, 1);
            gridPane.add(priceLabel, 0, 2);
            gridPane.add(priceTextField, 1, 2);
            gridPane.add(ticketTypeLabel, 0, 3);
            gridPane.add(ticketTypeChoiceBox, 1, 3);
            gridPane.add(saveButton, 1, 4);
            Scene editTicketScene = new Scene(gridPane, 500, 250);
            editTicketStage.setScene(editTicketScene);
            editTicketStage.show();
        } else {
            displayErrorAlert("Please select a ticket to edit.");
        }
    }
}
