package Model.GroundObjects;

import Model.Game;
import Model.Plane;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Objects;
import java.util.Random;

public class Tank extends Rectangle {
    private final int points = 8;
    public final double WIDTH = 50;
    public final double HEIGHT = 43.3;
    private int x;
    private int y;
    private Game game;

    public Tank(Game game) {
        super(50, 43.3);
        this.game = game;
        setX((double) Game.WIDTH / 2 - WIDTH / 2);
        setY((double) Game.HEIGHT / 2 - HEIGHT / 2);
        setFill(new ImagePattern(new Image(Objects.requireNonNull(Plane.class.getResource("/images/tank.png")).toExternalForm())));
    }
}
