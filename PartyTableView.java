/*
 Spyros Kanakopoylos 3212018071 
   Thodoris Theodorellis 3212018061
 */
package project;


import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PartyTableView {

    private ComboBox<Room> roomComboBox;

    public void createPartyTableView(Stage primaryStage) {
        
        //Example inserts 
        ArrayList<Party> parties = new ArrayList<>();
        Room room1 = new Room("Room 1", 1, 50.0f, true);
        Room room2 = new Room("Room 2", 2, 70.0f, true);

        parties.add(new Party(true, 12, "15:00", 1, 20, true, true, room1));
        parties.add(new Party(false, 18, "14:30", 2, 15, false, false, room2));
        //
        
        
        TableView<Party> partyTable = new TableView<>();

        TableColumn<Party, Integer> uniqueCodeCol = new TableColumn<>("Unique Code");
        uniqueCodeCol.setCellValueFactory(new PropertyValueFactory<>("partyUniqueCode"));

        TableColumn<Party, Integer> dayCol = new TableColumn<>("Day");
        dayCol.setCellValueFactory(new PropertyValueFactory<>("day"));

        TableColumn<Party, Integer> monthCol = new TableColumn<>("Month");
        monthCol.setCellValueFactory(new PropertyValueFactory<>("month"));

        TableColumn<Party, Integer> kidsCol = new TableColumn<>("Kids");
        kidsCol.setCellValueFactory(new PropertyValueFactory<>("kids"));

        TableColumn<Party, Boolean> menuOptionCol = new TableColumn<>("Menu Option");
        menuOptionCol.setCellValueFactory(new PropertyValueFactory<>("menuOption"));

        TableColumn<Party, Boolean> clownCol = new TableColumn<>("Clown Option");
        clownCol.setCellValueFactory(new PropertyValueFactory<>("clownOption"));

        TableColumn<Party, Boolean> cakeOptionCol = new TableColumn<>("Cake Option");
        cakeOptionCol.setCellValueFactory(new PropertyValueFactory<>("cakeOption"));

        TableColumn<Party, String> timeCol = new TableColumn<>("Time");
        timeCol.setCellValueFactory(new PropertyValueFactory<>("partyTime"));

        TableColumn<Party, Room> roomCol = new TableColumn<>("Room");
        roomCol.setCellValueFactory(new PropertyValueFactory<>("room"));

        partyTable.getColumns().addAll(uniqueCodeCol, clownCol, dayCol, timeCol, monthCol, kidsCol, menuOptionCol, cakeOptionCol, roomCol);

        Scene scene = new Scene(new BorderPane(partyTable), 900, 900);
        primaryStage.setScene(scene);
        primaryStage.show();


        partyTable.getItems().addAll(parties);

        primaryStage.setTitle("Party Information");
        primaryStage.show();

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
        root.setCenter(partyTable);

        root.setRight(buttonBox);
        int paddingValue = 20;
        int paddingValueBox = 30;

        root.setPadding(new Insets(paddingValue, paddingValue, paddingValue, paddingValue));
        BorderPane.setMargin(buttonBox, new Insets(150, 0, 0, paddingValueBox));

        Scene buttons_scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Party Information");
        primaryStage.setScene(buttons_scene);
        primaryStage.show();

        partyTable.setOnMouseClicked(e -> {
            Party selectedParty = partyTable.getSelectionModel().getSelectedItem();
            if (selectedParty != null) {

                System.out.println("");
            }
        });

        editButton.setOnAction(e -> {
            Party selectedParty = partyTable.getSelectionModel().getSelectedItem();
            if (selectedParty != null) {
                openEditScene(selectedParty, partyTable);
                partyTable.refresh();
            }
        });
        deleteButton.setOnAction(e -> {
            Party selectedParty = partyTable.getSelectionModel().getSelectedItem();
            if (selectedParty != null) {
                deleteSceneVerification(parties, selectedParty, partyTable);
            }
        });
        addButton.setOnAction(e -> openAddScene(primaryStage, partyTable));

    }

    private void openEditScene(Party party, TableView partyTable) {
        Stage editStage = new Stage();

        Scene editScene = createEditScene(editStage, party);

        editStage.setScene(editScene);
        editStage.setTitle("Edit Party");
        editStage.showAndWait();
        partyTable.refresh();
    }

    private Scene createEditScene(Stage editStage, Party party) {
        VBox editLayout = new VBox(20);
        editLayout.setPadding(new Insets(20));

        TextField uniqueCodeField = new TextField(String.valueOf(party.getPartyUniqueCode()));
        uniqueCodeField.setDisable(true); // Disable editing unique code
        TextField clownField = new TextField(party.getClownOption().toString());
        TextField dayField = new TextField(String.valueOf(party.getDay()));
        TextField monthField = new TextField(String.valueOf(party.getMonth()));
        TextField partyTimeField = new TextField(party.getPartyTime());
        TextField kidsField = new TextField(String.valueOf(party.getKids()));
        TextField menuOptionField = new TextField(String.valueOf(party.getMenuOption()));
        TextField cakeOptionField = new TextField(String.valueOf(party.getCakeOption()));

        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> {
            party.setClownOption(Boolean.valueOf(clownField.getText()));
            party.setDay(Integer.parseInt(dayField.getText()));
            party.setMonth(Integer.parseInt(monthField.getText()));
            party.setPartyTime(partyTimeField.getText());
            party.setKids(Integer.parseInt(kidsField.getText()));
            party.setMenuOption(Boolean.valueOf(menuOptionField.getText()));
            party.setCakeOption(Boolean.valueOf(cakeOptionField.getText()));
            editStage.close();
        });

        editLayout.getChildren().addAll(
                new Label("Unique Code:"), uniqueCodeField,
                new Label("Clown Option:"), clownField,
                new Label("Day:"), dayField,
                new Label("Month:"), monthField,
                new Label("Party Time:"), partyTimeField,
                new Label("Kids:"), kidsField,
                new Label("Menu Option:"), menuOptionField,
                new Label("Cake Option:"), cakeOptionField,
                saveButton
        );

        return new Scene(editLayout, 400, 800);
    }

    private void deleteSceneVerification(ArrayList<Party> parties, Party partyToDelete, TableView<Party> partyTable) {
        VBox deleteLayout = new VBox(40);

        Label confirmationLabel = new Label("Are you sure you want to delete this party?");

        Button confirmButton = new Button("Confirm");
        confirmButton.setOnAction(e -> {
            parties.remove(partyToDelete);
            partyTable.getItems().remove(partyToDelete); // Update TableView items after removing from parties list
            Stage stage = (Stage) confirmButton.getScene().getWindow();
            stage.close();
        });

        Button denyButton = new Button("Cancel");
        denyButton.setOnAction(e -> {
            Stage stage = (Stage) denyButton.getScene().getWindow();
            stage.close();
        });

        deleteLayout.getChildren().addAll(confirmationLabel, confirmButton, denyButton);

        Scene deleteScene = new Scene(deleteLayout, 300, 200);
        Stage deleteStage = new Stage();
        deleteStage.setScene(deleteScene);
        deleteStage.show();
    }

    private void openAddScene(Stage primaryStage, TableView<Party> partyTable) {
        Stage addStage = new Stage();

        VBox addLayout = new VBox(10);
        addLayout.setPadding(new Insets(10));

        TextField dayField = new TextField();
        TextField monthField = new TextField();
        TextField timeField = new TextField();
        TextField kidsField = new TextField();

        ToggleGroup clownGroup = new ToggleGroup();
        RadioButton clownYes = new RadioButton("Yes");
        RadioButton clownNo = new RadioButton("No");
        clownYes.setToggleGroup(clownGroup);
        clownNo.setToggleGroup(clownGroup);

        ToggleGroup menuGroup = new ToggleGroup();
        RadioButton menuYes = new RadioButton("Yes");
        RadioButton menuNo = new RadioButton("No");
        menuYes.setToggleGroup(menuGroup);
        menuNo.setToggleGroup(menuGroup);

        ToggleGroup cakeGroup = new ToggleGroup();
        RadioButton cakeYes = new RadioButton("Yes");
        RadioButton cakeNo = new RadioButton("No");
        cakeYes.setToggleGroup(cakeGroup);
        cakeNo.setToggleGroup(cakeGroup);
     

        roomComboBox = new ComboBox<>();
        for (Room room : Room.rooms) {
            if (room.isAvailable()) {
                roomComboBox.getItems().add(room);
            }
        }
        roomComboBox.setCellFactory(param -> new ListCell<Room>() {
            @Override
            protected void updateItem(Room room, boolean empty) {
                super.updateItem(room, empty);
                if (empty || room == null || room.getRoomName() == null) {
                    setText(null);
                } else {
                    setText(room.getRoomName());
                }
            }
        });

        roomComboBox.setButtonCell(new ListCell<Room>() {
            @Override
            protected void updateItem(Room room, boolean empty) {
                super.updateItem(room, empty);
                if (empty || room == null || room.getRoomName() == null) {
                    setText(null);
                } else {
                    setText(room.getRoomName());
                }
            }
        });

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            Party newParty = new Party(
                    clownYes.isSelected(),
                    Integer.parseInt(dayField.getText()),
                    timeField.getText(),
                    Integer.parseInt(monthField.getText()),
                    Integer.parseInt(kidsField.getText()),
                    menuYes.isSelected(),
                    cakeYes.isSelected(),
                    roomComboBox.getValue()
            );

            partyTable.getItems().add(newParty);
            addStage.close();
        });

        addLayout.getChildren().addAll(
                new Label("Clown Option:"), clownYes, clownNo,
                new Label("Day:"), dayField,
                new Label("Month:"), monthField,
                new Label("Time:"), timeField,
                new Label("Kids:"), kidsField,
                new Label("Menu Option:"), menuYes, menuNo,
                new Label("Cake Option:"), cakeYes, cakeNo,
                new Label("Room:"), roomComboBox,
                addButton
        );

        Scene addScene = new Scene(addLayout, 400, 500);
        addStage.setScene(addScene);
        addStage.setTitle("Add Party");
        addStage.showAndWait();

    }

}
