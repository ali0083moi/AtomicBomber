package Model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class Fire extends Rectangle {
    public final double WIDTH = 50;
    public final double HEIGHT = 50;
    private int x;
    private int y;
    private Game game;

    public Fire(Game game, double x) {
        super(50, 50);
        this.game = game;
        setX(x);
        setY(Game.HEIGHT - 180 - HEIGHT);
        this.setFill(new ImagePattern(new Image(Objects.requireNonNull(Plane.class.getResource("/images/fire-1.gif")).toExternalForm())));
    }

    public void setExploded(boolean b) {
        this.setRotate(0);
        this.setWidth(40);
        this.setHeight(40);
        this.setY(Game.HEIGHT - 180 - 40);
    }
}
