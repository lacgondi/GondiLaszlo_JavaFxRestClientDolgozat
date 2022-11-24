package com.example.gondilaszlo_javafxrestclient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class AddBusController{
    @FXML
    private TextField busField;
    @FXML
    private Spinner<Integer> delayField;
    @FXML
    private TextField destinationField;
    @FXML
    private Button submitButton;

    @FXML
    private void initialize() {
        SpinnerValueFactory.IntegerSpinnerValueFactory valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 200, 30);
        delayField.setValueFactory(valueFactory);
    }

    @FXML
    public void submitClick(ActionEvent actionEvent) {
        String bus = busField.getText().trim();
        int delay = delayField.getValue();
        String dest = destinationField.getText().trim();
        if (bus.isEmpty()) {
            alert(Alert.AlertType.WARNING, "Empty fields", "Bus license plate is required");
            return;
        }
        if (dest.isEmpty()) {
            alert(Alert.AlertType.WARNING, "Empty fields", "Destination is required");
            return;
        }
        Bus newBus = new Bus(0, bus, delay, dest);
        Gson converter = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String json = converter.toJson(newBus);
        Response response = null;
        try {
            response = RequestHandler.post(BusApplication.url, json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (response.getResponseCode() == 201) {
            alert(Alert.AlertType.WARNING, "Added", "Bus added!");
            busField.setText("");
            delayField.getValueFactory().setValue(30);
            destinationField.setText("");
        } else {
            String content = response.getContent();
            alert(Alert.AlertType.ERROR, "Error", content);
        }
    }

    protected void alert(Alert.AlertType alertType, String headerText, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

}
