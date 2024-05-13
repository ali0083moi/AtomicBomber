package Model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class AtomicBomb extends Rectangle {
    public final double WIDTH = 16;
    public final double HEIGHT = 30;
    private int x;
    private int y;
    private Game game;

    public AtomicBomb(Game game, double x, double y) {
        super(16, 30);
        this.game = game;
        setX(x);
        setY(y);
        setFill(new ImagePattern(new Image(Objects.requireNonNull(Plane.class.getResource("/images/atomic-bomb.png")).toExternalForm())));
    }

    public void setExploded(boolean b) {
        this.setRotate(0);
        this.setWidth(70);
        this.setHeight(70);
        this.setY(Game.HEIGHT - 180 - 70);
        this.setX(this.getX() - 20);
        setFill(new ImagePattern(new Image(Objects.requireNonNull(Plane.class.getResource("/images/explosion-2.gif")).toExternalForm())));
    }
}
