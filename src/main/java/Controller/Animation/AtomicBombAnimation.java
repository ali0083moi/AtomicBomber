package Controller.Animation;

import Model.AtomicBomb;
import Model.Bomb;
import Model.Game;
import Model.Plane;
import javafx.animation.Transition;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.Timer;
import java.util.TimerTask;

public class AtomicBombAnimation extends Transition{
    private final Game game;
    private final Pane root;
    private final AtomicBomb atomicBomb;
    private final Plane plane;
    private double speedX;
    private double speedY;
    private final double gravity = 0.02;
    private double angle;
    private boolean isFlipped = false;

    public AtomicBombAnimation(Game game, Pane root, AtomicBomb atomicBomb, Plane plane) {
        this.game = game;
        this.root = root;
        this.atomicBomb = atomicBomb;
        this.plane = plane;
        angle = plane.getAngle();
        isFlipped = plane.isFlipped();
        speedY = 0;
        if (isFlipped) {
            speedY = -speedY;
            atomicBomb.setRotate(-angle * 180 / Math.toDegrees(Math.PI));
        } else {
            atomicBomb.setRotate(-angle * 180 / Math.toDegrees(Math.PI));
        }
        this.setCycleCount(-1);
        this.setCycleDuration(Duration.millis(100));
        game.addAnimation(this);
    }

    @Override
    protected void interpolate(double v) {
        double y = atomicBomb.getY() - speedY;
        speedY -= gravity;
        if (y > Game.HEIGHT) {
            atomicBomb.setY(Game.HEIGHT);
        } else if (y < 0) {
            atomicBomb.setY(0);
        } else {
            atomicBomb.setY(y);
        }
        if (atomicBomb.getY() >= Game.HEIGHT - atomicBomb.HEIGHT - 180) {
            atomicBomb.setExploded(true);
            this.stop();
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            root.getChildren().remove(atomicBomb);
                            game.removeMyObject(atomicBomb);
                        }
                    });
                }
            }, 1000);
            return;
        }
    }
}
