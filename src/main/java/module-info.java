module com.example.gondilaszlo_javafxrestclient {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens com.example.gondilaszlo_javafxrestclient to javafx.fxml, com.google.gson;
    exports com.example.gondilaszlo_javafxrestclient;
}