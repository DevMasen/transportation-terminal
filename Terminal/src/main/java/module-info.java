module com.example.terminal {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.terminal to javafx.fxml;
    exports com.example.terminal;
}