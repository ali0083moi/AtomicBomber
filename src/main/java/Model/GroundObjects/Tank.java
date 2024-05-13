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
    public static final double WIDTH = 50;
    public static final double HEIGHT = 23.9;
    private int x;
    private int y;
    private Game game;

    public Tank(Game game, int x) {
        super(50, 23.9);
        this.game = game;
        setX((double) x);
        setY((double) Game.HEIGHT - HEIGHT - 180);
        setFill(new ImagePattern(new Image(Objects.requireNonNull(Plane.class.getResource("/images/tank.png")).toExternalForm())));
    }
}
