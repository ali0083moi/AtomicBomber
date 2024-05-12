package Model.GroundObjects;

import Model.Game;
import Model.Plane;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Objects;
import java.util.Random;

public class Tree extends Rectangle {
    private final int points = 0;
    public final double WIDTH = 30;
    public final double HEIGHT = 34.5;
    private int x;
    private int y;
    private Game game;

    public Tree(Game game) {
        super(30, 34.5);
        this.game = game;
        setX((double) Game.WIDTH / 2 - WIDTH / 2);
        setY((double) Game.HEIGHT / 2 - HEIGHT / 2);
        Random rand = new Random();
        int randomNumber = rand.nextInt(2) + 1;
        String treePath = "/images/tree-" + randomNumber + ".png";
        setFill(new ImagePattern(new Image(Objects.requireNonNull(Plane.class.getResource(treePath)).toExternalForm())));
    }
}
