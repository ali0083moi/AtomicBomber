package View;

import Controller.MainMenuController;
import Model.App;
import Model.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.net.URL;
import java.util.Objects;
import javafx.fxml.FXML;
public class MainMenu extends Application{
    @FXML
    public Text username = new Text();
    @FXML
    public ImageView avatarImageView = new ImageView();

    private final User loggedInUser = App.getLoggedInUser();

    final MainMenuController controller = new MainMenuController();
    public static Stage stage;
    @Override
    public void start(Stage stage) throws Exception {
        URL url = Main.class.getResource("/FXML/MainMenu.fxml");
        assert url != null;
        MainMenu.stage = stage;
        stageCreator(url, "Atomic Bomber - Main Menu");
        stage.setOnCloseRequest(event -> {
            // Save users data before closing
            App.saveUsers(App.getUsers());
            App.saveApp(App.getGuestUserCount());
        });
    }
    @FXML
    public void initialize() {
        Image avatar = new Image(Objects.requireNonNull(getClass().getResourceAsStream(loggedInUser.getAvatarPath())));
        avatarImageView.setImage(avatar);
        username.setText(loggedInUser.getUsername());
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

    public void startGame(MouseEvent mouseEvent) {
        controller.startGame(loggedInUser);
    }

    public void resumeGame(MouseEvent mouseEvent) {
    }

    public void openProfileMenu(MouseEvent mouseEvent) throws Exception {
        ProfileMenu profileMenu = new ProfileMenu();
        profileMenu.start(stage);
    }

    public void openScoreBoard(MouseEvent mouseEvent) throws Exception {
        URL url = Main.class.getResource("/FXML/ScoreBoard.fxml");
        assert url != null;
        stageCreator(url, "Atomic Bomber - Scoreboard");
    }

    public void openSettingsMenu(MouseEvent mouseEvent) throws Exception {
        URL url = Main.class.getResource("/FXML/SettingsMenu.fxml");
        assert url != null;
        stageCreator(url, "Atomic Bomber - Settings");
    }

    public void quitGame(MouseEvent mouseEvent) {
        controller.exit();
    }
}
