module AtomicBomber {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.media;

    exports View;
    opens View to javafx.fxml;
    opens Controller to javafx.fxml;
    opens Model to javafx.fxml;
    opens Enums to javafx.fxml;
}