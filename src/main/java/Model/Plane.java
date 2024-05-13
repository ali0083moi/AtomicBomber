package Model;

import Controller.Animation.AtomicBombAnimation;
import Controller.Animation.BombAnimation;
import Controller.Animation.ClusterBombAnimation;
import Controller.Animation.PlaneAnimation;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class Plane extends Rectangle {
    public final double WIDTH = 70;
    public final double HEIGHT = 17.5;
    private int x;
    private int y;
    private double angle;
    private boolean isFlipped;
    private Game game;
    private Pane root;

    public Plane(Game game, Pane root) {
        super(70, 17.5);
        this.game = game;
        this.root = root;
        setX((double) Game.WIDTH / 2 - WIDTH / 2);
        setY((double) Game.HEIGHT / 2 - HEIGHT / 2);
        setFill(new ImagePattern(new Image(Objects.requireNonNull(Plane.class.getResource("/images/plane.png")).toExternalForm())));
    }

    public void move() {
        // Move the plane
    }

    public void shootBomb() {
        Bomb bomb = new Bomb(game, this.getX() + WIDTH / 2, this.getY());
        BombAnimation planeAnimation = new BombAnimation(game, root, bomb, this);
        planeAnimation.play();
        game.addMyObject(bomb);
        root.getChildren().add(bomb);
    }

    public void shootAtomicBomb() {
        int atomicBombsCount = App.getLoggedInUser().getAtomicBombs();
        if (atomicBombsCount == 0) {
            return;
        }
        AtomicBomb atomicBomb = new AtomicBomb(game, this.getX() + WIDTH / 2, this.getY());
        AtomicBombAnimation atomicBombAnimation = new AtomicBombAnimation(game, root, atomicBomb, this);
        atomicBombAnimation.play();
        game.addMyObject(atomicBomb);
        root.getChildren().add(atomicBomb);
        App.getLoggedInUser().setAtomicBombs(atomicBombsCount - 1);
    }

    public void shootClusterBomb() {
        // Shoot cluster bomb
        int clusterBombsCount = App.getLoggedInUser().getClusterBombs();
        if (clusterBombsCount == 0) {
            return;
        }
        ClusterBomb clusterBomb = new ClusterBomb(game, this.getX() + WIDTH / 2, this.getY());
        ClusterBombAnimation clusterBombAnimation = new ClusterBombAnimation(game, root, clusterBomb, this);
        clusterBombAnimation.play();
        game.addMyObject(clusterBomb);
        root.getChildren().add(clusterBomb);
        App.getLoggedInUser().setClusterBombs(clusterBombsCount - 1);
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public boolean isFlipped() {
        return isFlipped;
    }

    public void setFlipped(boolean flipped) {
        isFlipped = flipped;
    }
}
