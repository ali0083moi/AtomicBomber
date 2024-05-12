package Model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class Cluster extends Rectangle {
    public final double WIDTH = 14.2;
    public final double HEIGHT = 25;
    private int x;
    private int y;
    private Game game;

    public Cluster(Game game) {
        super(14.2, 25);
        this.game = game;
        setX((double) Game.WIDTH / 2 - WIDTH / 2);
        setY((double) Game.HEIGHT / 2 - HEIGHT / 2);
        setFill(new ImagePattern(new Image(Objects.requireNonNull(Plane.class.getResource("/images/cluster.png")).toExternalForm())));
    }
}
