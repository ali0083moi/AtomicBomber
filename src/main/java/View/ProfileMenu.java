package View;

import Controller.MainMenuController;
import Controller.ProfileMenuController;
import Model.App;
import Model.User;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;

import static View.Main.main;
import static View.Main.stage;

public class ProfileMenu extends Application {
    private final User loggedInUser = App.getLoggedInUser();
    final ProfileMenuController controller = new ProfileMenuController();
    public static Stage stage;
    @FXML
    public Text username;
    @FXML
    public TextField usernameField;
    @FXML
    public TextField oldPasswordField;
    @FXML
    public TextField newPasswordField;
    @FXML
    public TextField confirmPasswordField;
    @FXML
    public ImageView avatarImageView;

    @Override
    public void start(Stage stage) throws Exception {
        URL url = Main.class.getResource("/FXML/ProfileMenu.fxml");
        assert url != null;
        ProfileMenu.stage = stage;
        stageCreator(url, "Atomic Bomber - Profile Menu");
        stage.setOnCloseRequest(event -> {
            // Save users data before closing
            App.saveUsers(App.getUsers());
            App.saveApp(App.getGuestUserCount());
        });
    }

    @FXML
    public void initialize() {
        username.setText(loggedInUser.getUsername());
        Image avatar = new Image(Objects.requireNonNull(getClass().getResourceAsStream(loggedInUser.getAvatarPath())));
        avatarImageView.setImage(avatar);
    }

    public void editUsername(MouseEvent mouseEvent) throws Exception {
        String newUsername = usernameField.getText().trim();
        if (controller.changeUsername(newUsername)) {
            username.setText(newUsername);
        }
        usernameField.clear();
    }

    public void editPassword(MouseEvent mouseEvent) throws Exception {
        String currentPassword = oldPasswordField.getText().trim();
        String newPassword = newPasswordField.getText().trim();
        String confirmPassword = confirmPasswordField.getText().trim();
        controller.changePassword(currentPassword, newPassword, confirmPassword);
        oldPasswordField.clear();
        newPasswordField.clear();
        confirmPasswordField.clear();
    }

    public void openAvatarMenu(MouseEvent mouseEvent) throws Exception {
        AvatarMenu avatarMenu = new AvatarMenu();
        avatarMenu.start(stage);
    }

    public void logout(MouseEvent mouseEvent) throws Exception {
        controller.logout();
    }

    public void deleteAccount(MouseEvent mouseEvent) throws Exception {
        controller.deleteAccount();
    }

    public void backToMainMenu(MouseEvent mouseEvent) throws Exception {
        MainMenu mainMenu = new MainMenu();
        mainMenu.start(stage);
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
}
