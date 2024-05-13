package Controller.Animation;

import Model.Game;
import Model.GroundObjects.Tank;
import Model.Plane;
import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class TankAnimation extends Transition {
    private final Game game;
    private final Pane root;
    private final Tank tank;
    private final double speed = 0.4;
    private boolean left, right;
    private boolean isFlipped = false;

    public TankAnimation(Game game, Pane root, Tank tank) {
        this.game = game;
        this.root = root;
        this.tank = tank;
        this.setCycleCount(-1);
        this.setCycleDuration(Duration.millis(100));
        game.addAnimation(this);
    }

    @Override
    protected void interpolate(double v) {
        double x = tank.getX();
        if (x > 0 - Tank.WIDTH && !isFlipped) {
            tank.setX(x - speed);
            if (x - speed <= 0 - Tank.WIDTH) {
                isFlipped = true;
                tank.setScaleX(-1);
            }
        } else if (x < Game.WIDTH + Tank.WIDTH && isFlipped) {
            tank.setX(x + speed);
            if (x + speed >= Game.WIDTH + Tank.WIDTH) {
                isFlipped = false;
                tank.setScaleX(1);
            }
        }
    }


}
