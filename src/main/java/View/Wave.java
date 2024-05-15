package View;

import Controller.Animation.TankAnimation;
import Controller.Animation.TruckAnimation;
import Controller.GameLauncherController;
import Controller.Animation.PlaneAnimation;
import Model.*;
import Model.GroundObjects.*;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TreeTableRow;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;


public class Wave extends Application {
    public ImageView groundImageView;
    private User loggedInUser = App.getLoggedInUser();
    public ImageView avatarImageView;
    private Game game;
    public static Stage stage;
    Pane root;
    private ArrayList<Integer> numbers = new ArrayList<>();

    private ArrayList<Integer> numbers2 = new ArrayList<>();
    private GameLauncherController controller;
    private MediaPlayer mediaPlayer;

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
        Wave.stage = stage;
        stageCreator(url, "Atomic Bomber - Game Launcher");
        stage.setOnCloseRequest(event -> {
            // Save users data before closing
            //App.saveUsers(App.getUsers());
            App.saveApp(App.getGuestUserCount());
        });
        controller = new GameLauncherController(game, root);
        String musicPath = "/sounds/music-" + loggedInUser.getLastWave() + ".mp3";
        Media media = new Media(getClass().getResource(musicPath).toExternalForm());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        buildingCreator();
        treeCreator();
        trenchCreator();
        tankCreator();
        truckCreator();
        planeCreator();
        //groundCreator();
    }

    private void truckCreator() {
        int size = numbers2.size();
        do {
            Random random = new Random();
            int x = random.nextInt(850);
            boolean flag = true;
            for (int number : numbers2) {
                if (x == number) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                Truck truck = new Truck(game, x);
                TruckAnimation truckAnimation = new TruckAnimation(game, root, truck);
                truckAnimation.play();
                game.addEnemyObject(truck);
                root.getChildren().add(truck);
                for (int i = (int) (x - Truck.WIDTH); i < x; i++) {
                    numbers2.add(i);
                }
            }
        } while (numbers2.size() != size + 3 * Truck.WIDTH);
    }

    private void tankCreator() {
            int size = numbers2.size();
            do {
                Random random = new Random();
                int x = random.nextInt(850);
                boolean flag = true;
                for (int number : numbers2) {
                    if (x == number) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    Tank tank = new Tank(game, x);
                    TankAnimation tankAnimation = new TankAnimation(game, root, tank);
                    tankAnimation.play();
                    tank.setTransition(tankAnimation);
                    game.addEnemyObject(tank);
                    root.getChildren().add(tank);
                    for (int i = (int) (x - Tank.WIDTH); i < x; i++) {
                        numbers2.add(i);
                    }
                }
            } while (numbers2.size() != size + 3 * Tank.WIDTH);
    }

    private void trenchCreator() {
        int size = numbers.size();
        do {
            Random random = new Random();
            int x = random.nextInt((int) (900 - Trench.WIDTH));
            boolean flag = true;
            for (int number : numbers) {
                if (x == number) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                Trench trench = new Trench(game, x);
                game.addEnemyObject(trench);
                root.getChildren().add(trench);
                for (int i = x; i < x + Trench.WIDTH; i++) {
                    numbers.add(i);
                }
                System.out.println("Trench created at " + x);
            }
        } while (numbers.size() != size + 2 * Trench.WIDTH);
    }

    private void treeCreator() throws NoSuchFieldException {
        int size = numbers.size();
        do {
            Random random = new Random();
            int x = random.nextInt((int) (900 - Tree.WIDTH));
            boolean flag = true;
            for (int number : numbers) {
                if (x == number) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                Tree tree = new Tree(game, x);
                game.addEnemyObject(tree);
                root.getChildren().add(tree);
                for (int i = x; i < x + Tree.WIDTH; i++) {
                    numbers.add(i);
                }
                System.out.println("Tree created at " + x);
            }
        } while (numbers.size() != size + 4 * Tree.WIDTH);
    }

    private void buildingCreator() {
        int size = numbers.size();
        do {
            Random random = new Random();
            int x = random.nextInt((int) (900 - Building.WIDTH));
            boolean flag = true;
            for (int number : numbers) {
                if (x == number) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                Building building = new Building(game, x);
                game.addEnemyObject(building);
                root.getChildren().add(building);
                for (int i = x - (int)(Building.WIDTH / 2); i < x + Building.WIDTH; i++) {
                    numbers.add(i);
                }
                System.out.println("Building created at " + x);
            }
        } while (numbers.size() != size + 3 * Building.WIDTH);
    }

    private void groundCreator() {
        Ground ground = new Ground(game);
        root.getChildren().add(ground);
    }

    private void planeCreator() {
        Plane plane = new Plane(game, root);
        PlaneAnimation planeAnimation = new PlaneAnimation(game, root, plane);
        planeAnimation.play();
        game.addMyObject(plane);
        root.getChildren().add(plane);
        plane.requestFocus();
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
            if (event.getCode() == KeyCode.SPACE) {
                plane.shootBomb();
            }
            if (event.getCode() == KeyCode.R) {
                plane.shootAtomicBomb();
            }
            if (event.getCode() == KeyCode.C) {
                plane.shootClusterBomb();
            }
            if (event.getCode() == KeyCode.ESCAPE) {
                controller.stop();
            }
            if (event.getCode() == KeyCode.P) {
                controller.changeWave();
            }
            if (event.getCode() == KeyCode.G) {
                controller.addAtomicBomb();
            }
            if (event.getCode() == KeyCode.COMMAND) {
                controller.addCluster();
            }
            if (event.getCode() == KeyCode.T) {
                controller.addTank();
            }
            if (event.getCode() == KeyCode.H) {
                controller.addLive();
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
        if (App.isBlackAndWhite()){
            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setSaturation(-1);
            root.setEffect(colorAdjust);
        }
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.setResizable(false);
        stage.centerOnScreen();
        String backgroundPath = "/Images/game-background-" + loggedInUser.getLastWave() + ".jpg";
        root.setStyle("-fx-background-image: url('" + backgroundPath + "')");
        stage.show();
    }

    public void openPauseMenu(MouseEvent mouseEvent) {
    }
}
