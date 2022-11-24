package com.example.gondilaszlo_javafxrestclient;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import com.google.gson.Gson;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class BusController {
    @FXML
    private Button insertButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;
    @FXML
    private TableView<Bus> busTable;
    @FXML
    private TableColumn<Bus, String> busCol;
    @FXML
    private TableColumn<Bus, Integer> delayCol;
    @FXML
    private TableColumn<Bus, String> destCol;

    @FXML
    private void initialize() {
        busCol.setCellValueFactory(new PropertyValueFactory<>("busID"));
        delayCol.setCellValueFactory(new PropertyValueFactory<>("delay"));
        destCol.setCellValueFactory(new PropertyValueFactory<>("destination"));
        Platform.runLater(() -> {
            try {
                loadPeopleFromServer();
            } catch (IOException e) {
                e.getStackTrace();
                Platform.exit();
            }
        });
    }

    private void loadPeopleFromServer() throws IOException {
        Response response = RequestHandler.get(BusApplication.url);
        String content = response.getContent();
        Gson converter = new Gson();
        Bus[] buses = converter.fromJson(content, Bus[].class);
        busTable.getItems().clear();
        for (Bus person : buses) {
            busTable.getItems().add(person);
        }
    }

    public void insertClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(BusApplication.class.getResource("add-bus-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 640, 480);
            Stage stage = new Stage();
            stage.setTitle("Add buses");
            stage.setScene(scene);
            stage.show();
            insertButton.setDisable(true);
            updateButton.setDisable(true);
            deleteButton.setDisable(true);
            stage.setOnCloseRequest(event -> {
                insertButton.setDisable(false);
                updateButton.setDisable(false);
                deleteButton.setDisable(false);
                try {
                    loadPeopleFromServer();
                } catch (IOException e) {
                    e.getMessage();
                }
            });
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public void updateClick(ActionEvent actionEvent) {
        int selectedIndex = busTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            alert(Alert.AlertType.WARNING, "Invalid selection", "Please select a bus from the list");
            return;
        }
        Bus selected = busTable.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(BusApplication.class.getResource("edit-bus-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 640, 480);
            Stage stage = new Stage();
            stage.setTitle("Edit Bus");
            stage.setScene(scene);
            EditBusController controller = fxmlLoader.getController();
            controller.setBus(selected);
            stage.show();
            insertButton.setDisable(true);
            updateButton.setDisable(true);
            deleteButton.setDisable(true);
            stage.setOnHidden(event -> {
                insertButton.setDisable(false);
                updateButton.setDisable(false);
                deleteButton.setDisable(false);
                try {
                    loadPeopleFromServer();
                } catch (IOException e) {
                    alert(Alert.AlertType.ERROR, "An error occurred while communicating with the server", e.getMessage());
                }
            });
        } catch (IOException e) {
            alert(Alert.AlertType.ERROR, "Could not load form", e.getMessage());
        }

    }

    public void deleteClick(ActionEvent actionEvent) {
        int selectedIndex = busTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            alert(Alert.AlertType.WARNING, "Invalid selection", "Please select a bus from the list");
            return;
        }

        Bus selected = busTable.getSelectionModel().getSelectedItem();
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setHeaderText(String.format("Are you sure you want to delete %s?", selected.getBusID()));
        Optional<ButtonType> optionalButtonType = confirmation.showAndWait();
        if (optionalButtonType.isEmpty()) {
            System.err.println("Unknown error occurred");
            return;
        }
        ButtonType clickedButton = optionalButtonType.get();
        if (clickedButton.equals(ButtonType.OK)) {
            String url = BusApplication.url + "/" + selected.getId();
            try {
                RequestHandler.delete(url);
                loadPeopleFromServer();
            } catch (IOException e) {
                alert(Alert.AlertType.ERROR, "An error occurred while communicating with the server", e.getMessage());
            }
        }
    }

    protected void alert(Alert.AlertType alertType, String headerText, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
}