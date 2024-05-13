package Model.GroundObjects;

import Model.Game;
import Model.Plane;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class Trench extends Rectangle {
    private final int points = 2;
    public static final double WIDTH = 20;
    public static final double HEIGHT = 15.7;
    private int x;
    private int y;
    private Game game;

    public Trench(Game game, int x) {
        super(20, 15.7);
        this.game = game;
        setX((double) x);
        setY((double) Game.HEIGHT - HEIGHT - 180);
        setFill(new ImagePattern(new Image(Objects.requireNonNull(Plane.class.getResource("/images/trench.png")).toExternalForm())));
    }
}
