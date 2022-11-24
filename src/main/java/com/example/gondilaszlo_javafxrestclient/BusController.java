package com.example.gondilaszlo_javafxrestclient;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import com.google.gson.Gson;

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
    }

    public void updateClick(ActionEvent actionEvent) {
    }

    public void deleteClick(ActionEvent actionEvent) {
    }
}