package Model.GroundObjects;

import Model.Game;
import Model.Plane;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Objects;
import java.util.Random;

public class Building extends Rectangle {
    private final int points = 10;
    public final double WIDTH = 60;
    public final double HEIGHT = 42;
    private int x;
    private int y;
    private Game game;

    public Building(Game game, int x) {
        super(60, 42);
        this.game = game;
        setX((double) x);
        setY((double) Game.HEIGHT - 180 - HEIGHT);
        Random rand = new Random();
        int randomNumber = rand.nextInt(3) + 1;
        String buildingPath = "/images/house-" + randomNumber + ".png";
        setFill(new ImagePattern(new Image(Objects.requireNonNull(Plane.class.getResource(buildingPath)).toExternalForm())));
    }

}
