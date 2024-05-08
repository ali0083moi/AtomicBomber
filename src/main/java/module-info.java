module AtomicBomber {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.media;
    requires com.google.gson;

    exports View;
    opens View to javafx.fxml;
    opens Controller to javafx.fxml;
    opens Model to com.google.gson;
    opens Enums to javafx.fxml;
}