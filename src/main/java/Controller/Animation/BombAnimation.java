package Controller.Animation;

import Model.Bomb;
import Model.Game;
import Model.Plane;
import javafx.animation.Transition;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.Timer;
import java.util.TimerTask;

public class BombAnimation extends Transition {

    private final Game game;
    private final Pane root;
    private final Bomb bomb;
    private final Plane plane;
    private double speedX;
    private double speedY;
    private final double gravity = 0.02;
    private double angle;
    private boolean isFlipped = false;

    public BombAnimation(Game game, Pane root, Bomb bomb, Plane plane) {
        this.game = game;
        this.root = root;
        this.bomb = bomb;
        this.plane = plane;
        angle = plane.getAngle();
        isFlipped = plane.isFlipped();
        speedX = 1.5 * Math.cos(Math.toRadians(angle));
        speedY = Math.sin(Math.toRadians(angle));
        if (isFlipped) {
            speedY = -speedY;
            speedX = -speedX;
            bomb.setRotate(-angle * 180 / Math.toDegrees(Math.PI) + 15);
        } else {
            bomb.setRotate(-angle * 180 / Math.toDegrees(Math.PI) - 15);
        }
        this.setCycleCount(-1);
        this.setCycleDuration(Duration.millis(100));
        game.addAnimation(this);
    }
    @Override
    protected void interpolate(double v) {
        double y = bomb.getY() - speedY;
        double x = bomb.getX() + speedX;
        speedY -= gravity;

        if (y > Game.HEIGHT) {
            bomb.setY(Game.HEIGHT);
            bomb.setX(x);
        }
        else if (y < 0) {
            bomb.setY(0);
            bomb.setX(x);
        }
        else if (x > Game.WIDTH) {
            bomb.setX(Game.WIDTH);
            bomb.setY(y);
        }
        else {
            bomb.setY(y);
            bomb.setX(x);
        }
        if (bomb.getY() >= Game.HEIGHT - bomb.HEIGHT - 180) {
            bomb.setExploded(true);
            this.stop();
            //check if the bomb is in the range of the enemy rectangle and remove it
            //private Group allEnemyObjects = new Group();
            // now write the code to do this

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            root.getChildren().remove(bomb);
                            game.removeMyObject(bomb);
                        }
                    });
                }
            }, 1000);
            return;
        }
    }
}
