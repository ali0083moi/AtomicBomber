package Controller.Animation;

import Model.Game;
import Model.GroundObjects.Tank;
import Model.GroundObjects.Tree;
import Model.GroundObjects.Truck;
import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class TruckAnimation extends Transition {
    private final Game game;
    private final Pane root;
    private final Truck truck;
    private final double speed = 0.6;
    private boolean left, right;
    private boolean isFlipped = false;

    public TruckAnimation(Game game, Pane root, Truck truck) {
        this.game = game;
        this.root = root;
        this.truck = truck;
        this.setCycleCount(-1);
        this.setCycleDuration(Duration.millis(100));
        game.addAnimation(this);
    }

    @Override
    protected void interpolate(double v) {
        double x = truck.getX();
        if (x > 0 - Tank.WIDTH && !isFlipped) {
            truck.setX(x - speed);
            if (x - speed <= 0 - Tank.WIDTH) {
                isFlipped = true;
                truck.setScaleX(-1);
            }
        } else if (x < Game.WIDTH + Tank.WIDTH && isFlipped) {
            truck.setX(x + speed);
            if (x + speed >= Game.WIDTH + Tank.WIDTH) {
                isFlipped = false;
                truck.setScaleX(1);
            }
        }
    }

}
