package Controller;

import Controller.Animation.TankAnimation;
import Model.App;
import Model.Game;
import Model.GroundObjects.Tank;
import javafx.scene.layout.Pane;

import java.util.Random;

public class GameLauncherController {
    private Game game;
    private Pane root;

    public GameLauncherController(Game game, Pane root) {
        this.game = game;
        this.root = root;
    }
    public void stop() {
    }

    public void changeWave() {
    }

    public void addAtomicBomb() {
        App.getLoggedInUser().setAtomicBombs(App.getLoggedInUser().getAtomicBombs() + 1);
    }

    public void addCluster() {
        App.getLoggedInUser().setClusterBombs(App.getLoggedInUser().getClusterBombs() + 1);
    }

    public void addTank() {
        Random random = new Random();
        int x = random.nextInt(850);
        Tank tank = new Tank(game, x);
        TankAnimation tankAnimation = new TankAnimation(game, root, tank);
        tankAnimation.play();
        game.addEnemyObject(tank);
        root.getChildren().add(tank);
    }

    public void addLive() {
    }
}
