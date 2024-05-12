package View;

import Controller.RegisterMenuController;
import Model.App;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.net.URL;
import java.util.Objects;

public class RegisterMenu extends Application {
    final RegisterMenuController controller = new RegisterMenuController();
    public static Stage stage;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @Override
    public void start(Stage stage) throws Exception {
        URL url = Main.class.getResource("/FXML/RegisterMenu.fxml");
        assert url != null;
        RegisterMenu.stage = stage;
        stageCreator(url, "Atomic Bomber");
        stage.setOnCloseRequest(event -> {
            // Save users data before closing
            //App.saveUsers(App.getUsers());
            App.saveApp(App.getGuestUserCount());
        });
    }

    public void signup(MouseEvent mouseEvent) throws Exception {
        String usernameStr = username.getText().trim();
        String passwordStr = password.getText().trim();
        controller.signup(usernameStr, passwordStr);
    }

    public void login(MouseEvent mouseEvent) throws Exception {
        String usernameStr = username.getText().trim();
        String passwordStr = password.getText().trim();
        controller.login(usernameStr, passwordStr);
    }

    public void guestLogin(MouseEvent mouseEvent) throws Exception {
        String usernameStr = "Guest";
        String passwordStr = "Guest#";
        int guestNumber = App.getGuestUserCount();
        usernameStr += guestNumber;
        controller.guestUserLogin(usernameStr, passwordStr);
    }

    public void openSignupPage(MouseEvent mouseEvent) throws Exception {
        URL url = Main.class.getResource("/FXML/SignupMenu.fxml");
        assert url != null;
        stageCreator(url, "Atomic Bomber - Signup");
    }
    public void openLoginPage(MouseEvent mouseEvent) throws Exception {
        URL url = Main.class.getResource("/FXML/LoginMenu.fxml");
        assert url != null;
        stageCreator(url, "Atomic Bomber - Login");
    }

    public void backToRegisterMenu(MouseEvent mouseEvent) throws Exception {
        start(stage);
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
