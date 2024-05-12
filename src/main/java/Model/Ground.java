package Model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class Ground extends Rectangle {
    public final double WIDTH = 900;
    public final double HEIGHT = 180;
    Game game;

    public Ground(Game game) {
        super(900, 180);
        this.game = game;
        setX((double) Game.WIDTH / 2 - WIDTH / 2);
        setY((double) Game.HEIGHT - HEIGHT);
        setFill(new ImagePattern(new Image(Objects.requireNonNull(Plane.class.getResource("/images/ground1.png")).toExternalForm())));
    }
}
