package Model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class RadioActiveBomb extends Rectangle {
    public final double WIDTH = 16;
    public final double HEIGHT = 30;
    private int x;
    private int y;
    private Game game;

    public RadioActiveBomb(Game game) {
        super(16, 30);
        this.game = game;
        setX((double) Game.WIDTH / 2 - WIDTH / 2);
        setY((double) Game.HEIGHT / 2 - HEIGHT / 2);
        setFill(new ImagePattern(new Image(Objects.requireNonNull(Plane.class.getResource("/images/atomic-bomb.png")).toExternalForm())));
    }
}
