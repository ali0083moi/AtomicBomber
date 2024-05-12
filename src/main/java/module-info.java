module AtomicBomber {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.media;
    requires com.google.gson;

    exports View;
    exports Controller;
    exports Model;
    opens View to javafx.fxml, com.google.gson;
    opens Controller to javafx.fxml, com.google.gson;
    opens Model to com.google.gson, javafx.base, javafx.fxml;
    opens Enums to javafx.fxml;
}