package View;

import Controller.MainMenuController;
import Controller.ScoreBoardController;
import Model.App;
import Model.User;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class ScoreBoard extends Application {
    private final User loggedInUser = App.getLoggedInUser();
    final ScoreBoardController controller = new ScoreBoardController();

    public static Stage stage;
    public TableView scoreboardTable;
    public TableColumn rankColumn;

    @Override
    public void start(Stage stage) throws Exception {
        URL url = Main.class.getResource("/FXML/ScoreBoard.fxml");
        assert url != null;
        ScoreBoard.stage = stage;
        stageCreator(url, "Atomic Bomber - Scoreboard");
        scoreboardTable = (TableView) stage.getScene().lookup("#scoreboardTable");
        controller.setScoreboardTable(scoreboardTable);
        stage.setOnCloseRequest(event -> {
            // Save users data before closing
            //App.saveUsers(App.getUsers());
            App.saveApp(App.getGuestUserCount());
        });
    }

    public void stageCreator(URL url, String title) throws Exception {
        AnchorPane root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        Image icon = new Image(Objects.requireNonNull(Main.class.getResource("/images/icon.jpg")).toExternalForm());
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }

    public void backToMainMenu(MouseEvent mouseEvent) throws Exception {
        MainMenu mainMenu = new MainMenu();
        mainMenu.start(stage);
    }
}
