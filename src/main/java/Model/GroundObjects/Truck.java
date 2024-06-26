package Model.GroundObjects;

import Model.Game;
import Model.Plane;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Objects;
import java.util.Random;

public class Truck extends Rectangle {
    private final int points = 4;
    public static final double WIDTH = 40;
    public static final double HEIGHT = 22.2;
    private int x;
    private int y;
    private Game game;

    public Truck(Game game, int x) {
        super(40, 22.2);
        this.game = game;
        setX((double) x);
        setY((double) Game.HEIGHT - HEIGHT - 180);
        setFill(new ImagePattern(new Image(Objects.requireNonNull(Plane.class.getResource("/images/truck.png")).toExternalForm())));
    }
}
