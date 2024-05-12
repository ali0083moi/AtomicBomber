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

    public BombLet(Game game) {
        super(10, 8.4);
        this.game = game;
        setX((double) Game.WIDTH / 2 - WIDTH / 2);
        setY((double) Game.HEIGHT / 2 - HEIGHT / 2);
        setFill(new ImagePattern(new Image(Objects.requireNonNull(Plane.class.getResource("/images/bomblet.png")).toExternalForm())));
    }
}
