module com.dassoop.jdkswitchapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.datatransfer;


    opens com.dassoop.jdkswitchapp to javafx.fxml;
    exports com.dassoop.jdkswitchapp;
}