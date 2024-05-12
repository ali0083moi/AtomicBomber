package Model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class Bomb extends Rectangle {
    public final double WIDTH = 7.7;
    public final double HEIGHT = 25;
    private int x;
    private int y;
    private Game game;

    public Bomb(Game game) {
        super(7.7, 25);
        this.game = game;
        setX((double) Game.WIDTH / 2 - WIDTH / 2);
        setY((double) Game.HEIGHT / 2 - HEIGHT / 2);
        setFill(new ImagePattern(new Image(Objects.requireNonNull(Plane.class.getResource("/images/bomb.png")).toExternalForm())));
    }
}
