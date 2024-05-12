package Model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class Plane extends Rectangle {
    public final double WIDTH = 70;
    public final double HEIGHT = 17.5;
    private int x;
    private int y;
    private Game game;

    public Plane(Game game) {
        super(70, 17.5);
        this.game = game;
        setX((double) Game.WIDTH / 2 - WIDTH / 2);
        setY((double) Game.HEIGHT / 2 - HEIGHT / 2);
        setFill(new ImagePattern(new Image(Objects.requireNonNull(Plane.class.getResource("/images/plane.png")).toExternalForm())));
    }

    public void move() {
        // Move the plane
    }

    public void shoot() {
        // Shoot a bullet
    }

    public void dropClusterBomb() {
        // Drop a cluster bomb
    }

    public void dropRadioActiveBomb() {
        // Drop a radio active bomb
    }
}
