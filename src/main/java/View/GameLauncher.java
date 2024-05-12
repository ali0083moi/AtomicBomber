package View;

import Controller.GameLauncherController;
import Controller.Animation.PlaneAnimation;
import Controller.MainMenuController;
import Model.*;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;


public class GameLauncher extends Application {
    public ImageView groundImageView;
    private User loggedInUser = App.getLoggedInUser();
    public ImageView avatarImageView;
    private Game game;
    final GameLauncherController controller = new GameLauncherController();
    public static Stage stage;
    Pane root;

    private void setSize(Pane root) {
        root.setPrefSize(Game.WIDTH, Game.HEIGHT);
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(javafx.stage.Stage stage) throws Exception {
        URL url = Main.class.getResource("/FXML/GameLauncher.fxml");
        assert url != null;
        GameLauncher.stage = stage;
        stageCreator(url, "Atomic Bomber - Game Launcher");
        stage.setOnCloseRequest(event -> {
            // Save users data before closing
            //App.saveUsers(App.getUsers());
            App.saveApp(App.getGuestUserCount());
        });
        planeCreator();
        //groundCreator();
    }

    private void groundCreator() {
        Ground ground = new Ground(game);
        root.getChildren().add(ground);
    }

    private void planeCreator() {
        Plane plane = new Plane(game);
        PlaneAnimation planeAnimation = new PlaneAnimation(game, root, plane);
        planeAnimation.play();
        root.getChildren().add(plane);
        plane.requestFocus();
        game.addMyObject(plane);
        plane.setOnKeyPressed(event -> {
            plane.requestFocus();
            if (App.isArrowKeys()) {
                if (event.getCode() == KeyCode.RIGHT) {
                    planeAnimation.setRight(true);
                }
                if (event.getCode() == KeyCode.LEFT) {
                    planeAnimation.setLeft(true);
                }
                if (event.getCode() == KeyCode.UP) {
                    planeAnimation.setUp(true);
                }
                if (event.getCode() == KeyCode.DOWN) {
                    planeAnimation.setDown(true);
                }
            } else {
                if (event.getCode() == KeyCode.D) {
                    planeAnimation.setRight(true);
                }
                if (event.getCode() == KeyCode.A) {
                    planeAnimation.setLeft(true);
                }
                if (event.getCode() == KeyCode.W) {
                    planeAnimation.setUp(true);
                }
                if (event.getCode() == KeyCode.S) {
                    planeAnimation.setDown(true);
                }
            }
        });
        plane.setOnKeyReleased(event -> {
            plane.requestFocus();
            if (App.isArrowKeys()) {
                if (event.getCode() == KeyCode.RIGHT) {
                    planeAnimation.setRight(false);
                }
                if (event.getCode() == KeyCode.LEFT) {
                    planeAnimation.setLeft(false);
                }
                if (event.getCode() == KeyCode.UP) {
                    planeAnimation.setUp(false);
                }
                if (event.getCode() == KeyCode.DOWN) {
                    planeAnimation.setDown(false);
                }
            } else {
                if (event.getCode() == KeyCode.D) {
                    planeAnimation.setRight(false);
                }
                if (event.getCode() == KeyCode.A) {
                    planeAnimation.setLeft(false);
                }
                if (event.getCode() == KeyCode.W) {
                    planeAnimation.setUp(false);
                }
                if (event.getCode() == KeyCode.S) {
                    planeAnimation.setDown(false);
                }
            }
        });
    }

    @FXML
    public void initialize() {
        Image avatar = new Image(Objects.requireNonNull(getClass().getResourceAsStream(loggedInUser.getAvatarPath())));
        avatarImageView.setImage(avatar);
        String groundPath = "/Images/ground" + loggedInUser.getLastWave() + ".png";
        Image ground = new Image(Objects.requireNonNull(getClass().getResourceAsStream(groundPath)));
        groundImageView.setImage(ground);
        groundImageView.setX(0);
        groundImageView.setY(Game.HEIGHT - 180);
    }

    public void stageCreator(URL url, String title) throws Exception {
        root = FXMLLoader.load(url);
        setSize(root);
        Scene scene = new Scene(root);
        Image icon = new Image(Objects.requireNonNull(Main.class.getResource("/images/icon.jpg")).toExternalForm());
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.setResizable(false);
        stage.centerOnScreen();
        String backgroundPath = "/Images/game-background-" + loggedInUser.getLastWave() + ".jpg";
        root.setStyle("-fx-background-image: url('" + backgroundPath + "')");
        stage.show();
    }
}
