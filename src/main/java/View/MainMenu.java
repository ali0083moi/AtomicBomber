package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.net.URL;
public class MainMenu extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        URL url = Main.class.getResource("/FXML/MainMenu.fxml");
        assert url != null;
        AnchorPane root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Atomic Bomber");
        stage.show();
    }
}
