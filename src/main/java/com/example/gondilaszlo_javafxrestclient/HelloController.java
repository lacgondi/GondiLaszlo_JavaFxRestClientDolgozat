package com.example.gondilaszlo_javafxrestclient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class HelloController {
    @FXML
    private Button insertButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;
    @FXML
    private TableView<Bus> busTable;
    @FXML
    private TableColumn<Bus, String> idCol;
    @FXML
    private TableColumn<Bus, String> busCol;
    @FXML
    private TableColumn<Bus, String> delayCol;
    @FXML
    private TableColumn<Bus, Integer> destCol;


    public void insertClick(ActionEvent actionEvent) {
    }

    public void updateClick(ActionEvent actionEvent) {
    }

    public void deleteClick(ActionEvent actionEvent) {
    }
}