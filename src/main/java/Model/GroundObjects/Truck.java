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
    public final double WIDTH = 40;
    public final double HEIGHT = 22.2;
    private int x;
    private int y;
    private Game game;

    public Truck(Game game) {
        super(40, 22.2);
        this.game = game;
        setX((double) Game.WIDTH / 2 - WIDTH / 2);
        setY((double) Game.HEIGHT / 2 - HEIGHT / 2);
        setFill(new ImagePattern(new Image(Objects.requireNonNull(Plane.class.getResource("/images/truck.png")).toExternalForm())));
    }
}
