module AtomicBomber {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.media;

    exports View;
    opens View to javafx.fxml;
}