package Model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class ClusterBomb extends Rectangle {
    public final double WIDTH = 14.2;
    public final double HEIGHT = 25;
    private int x;
    private int y;
    private Game game;

    public ClusterBomb(Game game, double x, double y) {
        super(14.2, 25);
        this.game = game;
        setX(x);
        setY(y);
        setFill(new ImagePattern(new Image(Objects.requireNonNull(Plane.class.getResource("/images/cluster.png")).toExternalForm())));
    }

    public void setExploded(boolean b) {
        this.setWidth(30);
        this.setHeight(30);
        this.setY(Game.HEIGHT - 180 - 30);
        setFill(new ImagePattern(new Image(Objects.requireNonNull(Plane.class.getResource("/images/explosion-1.gif")).toExternalForm())));
    }
}
