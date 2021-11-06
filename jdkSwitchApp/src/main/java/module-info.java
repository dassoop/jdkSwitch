module com.dassoop.jdkswitchapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.dassoop.jdkswitchapp to javafx.fxml;
    exports com.dassoop.jdkswitchapp;
}