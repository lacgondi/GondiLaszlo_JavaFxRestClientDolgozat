package com.example.gondilaszlo_javafxrestclient;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import com.google.gson.Gson;
import javafx.stage.Stage;

import java.io.IOException;

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
    }

    public void deleteClick(ActionEvent actionEvent) {
    }
}