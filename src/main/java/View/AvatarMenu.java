package View;

import Controller.AvatarMenuController;
import Model.App;
import Model.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import javafx.fxml.FXML;

public class AvatarMenu extends Application {
    AvatarMenuController controller = new AvatarMenuController();
    private final User loggedInUser = App.getLoggedInUser();
    @FXML
    public ImageView avatarImageView = new ImageView();
    @FXML
    Button openAvatarFromFile;
    @FXML
    public Text username = new Text();
    public static Stage stage;
    private static String avatarPath;

    @Override
    public void start(Stage stage) throws Exception {
        URL url = Main.class.getResource("/FXML/AvatarMenu.fxml");
        assert url != null;
        AvatarMenu.stage = stage;
        stageCreator(url, "Atomic Bomber - Avatar Menu");
    }

    @FXML
    public void initialize() {
        Image avatar = new Image(Objects.requireNonNull(getClass().getResourceAsStream(loggedInUser.getAvatarPath())));
        avatarImageView.setImage(avatar);
        username.setText(loggedInUser.getUsername());
        avatarImageView.setOnDragOver(event -> {
            if (event.getGestureSource() != avatarImageView && event.getDragboard().hasFiles()) {
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
            event.consume();
        });
        avatarImageView.setOnDragDropped(event -> {
            Dragboard dragboard = event.getDragboard();
            boolean success = false;
            if (dragboard.hasFiles()) {
                List<File> files = dragboard.getFiles();
                if (files.size() == 1) {
                    File file = files.get(0);
                    // Handle the dropped file (e.g., copy it to a destination directory)
                    controller.handleDroppedImage(file, avatarImageView);
                    success = true;
                }
            }
            event.setDropCompleted(success);
            event.consume();
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

    public void chooseAvatar(MouseEvent mouseEvent) {
        ImageView imageView = (ImageView) mouseEvent.getTarget();
        controller.chooseAvatar(imageView, avatarImageView);
    }

    public void backToProfileMenu(MouseEvent mouseEvent) throws Exception {
        ProfileMenu profileMenu = new ProfileMenu();
        profileMenu.start(stage);
    }

    public void setNewAvatar(MouseEvent mouseEvent) {
        controller.setNewAvatar();
    }

    public void openAvatarFromFile(MouseEvent mouseEvent) {
        controller.setAvatarFromFile(openAvatarFromFile, avatarImageView);
    }

    public static String getAvatarPath() {
        return avatarPath;
    }

    public static void setAvatarPath(String avatarPath) {
        AvatarMenu.avatarPath = avatarPath;
    }
}
