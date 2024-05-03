package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;

public class LoaderController {
    @FXML
    private ProgressBar progressBar;

    public void updateProgress(double progress) {
        progressBar.setProgress(progress);
    }
}
