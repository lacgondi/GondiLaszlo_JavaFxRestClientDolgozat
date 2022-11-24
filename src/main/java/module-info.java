module com.example.gondilaszlo_javafxrestclient {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.gondilaszlo_javafxrestclient to javafx.fxml;
    exports com.example.gondilaszlo_javafxrestclient;
}