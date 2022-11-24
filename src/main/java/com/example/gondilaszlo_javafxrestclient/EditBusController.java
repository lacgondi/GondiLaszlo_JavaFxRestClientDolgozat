package com.example.gondilaszlo_javafxrestclient;

import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class EditBusController {
    @FXML
    private TextField busField;
    @FXML
    private Spinner<Integer> delayField;
    @FXML
    private TextField destinationField;
    @FXML
    private Button updateButton;

    private Bus bus;

    public void setBus(Bus bus) {
        this.bus = bus;
        busField.setText(this.bus.getBusID());
        delayField.getValueFactory().setValue(this.bus.getDelay());
        destinationField.setText(this.bus.getDestination());
    }

    @FXML
    private void initialize() {
        SpinnerValueFactory.IntegerSpinnerValueFactory valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 200, 30);
        delayField.setValueFactory(valueFactory);
    }

    @FXML
    public void updateClick(ActionEvent actionEvent) {
        String bus = busField.getText().trim();
        String destination = destinationField.getText().trim();
        int delay = delayField.getValue();
        if (bus.isEmpty()) {
            alert(Alert.AlertType.WARNING, "Empty fields", "Bus license plate is required");
            return;
        }
        if (destination.isEmpty()) {
            alert(Alert.AlertType.WARNING, "Empty fields", "Destination is required");
            return;
        }
        this.bus.setBusID(bus);
        this.bus.setDestination(destination);
        this.bus.setDelay(delay);
        Gson converter = new Gson();
        String json = converter.toJson(this.bus);
        try {
            String url = BusApplication.url + "/" + this.bus.getId();
            Response response = RequestHandler.put(url, json);
            if (response.getResponseCode() == 200) {
                Stage stage = (Stage) this.updateButton.getScene().getWindow();
                stage.close();
            } else {
                String content = response.getContent();
                alert(Alert.AlertType.ERROR, "Error", content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void alert(Alert.AlertType alertType, String headerText, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
}
