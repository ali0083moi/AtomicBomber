package Model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class BombLet extends Rectangle {
    public final double WIDTH = 10;
    public final double HEIGHT = 8.4;
    private int x;
    private int y;
    private Game game;

    public BombLet(Game game, double x, double y) {
        super(10, 8.4);
        this.game = game;
        setX(x);
        setY(y);
        setFill(new ImagePattern(new Image(Objects.requireNonNull(Plane.class.getResource("/images/bomblet.png")).toExternalForm())));
    }

    public void setExploded(boolean b) {
        this.setWidth(40);
        this.setHeight(40);
        this.setY(Game.HEIGHT - 180 - 40);
        setFill(new ImagePattern(new Image(Objects.requireNonNull(Plane.class.getResource("/images/explosion-3.gif")).toExternalForm())));
    }
}
