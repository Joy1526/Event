module org.example.event {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires javafx.graphics;

    opens org.example.event to javafx.fxml;
    exports org.example.event;
}