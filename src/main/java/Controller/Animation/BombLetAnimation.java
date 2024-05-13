package Controller.Animation;

import Model.BombLet;
import Model.Game;
import Model.Plane;
import javafx.animation.PauseTransition;
import javafx.animation.Transition;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class BombLetAnimation extends Transition {
    private final Game game;
    private final Pane root;
    private final BombLet bombLet;
    private final Plane plane;
    private double speedX;
    private double speedY;
    private final double gravity = 0.02;
    private double angle;
    private boolean isFlipped = false;

    public BombLetAnimation(Game game, Pane root, BombLet bombLet, Plane plane) {
        this.game = game;
        this.root = root;
        this.bombLet = bombLet;
        this.plane = plane;
        Random random = new Random();
        angle = random.nextInt(180);
        speedX = 1.5 * Math.cos(Math.toRadians(angle));
        speedY = Math.sin(Math.toRadians(angle));
        this.setCycleCount(-1);
        this.setCycleDuration(Duration.millis(100));
        game.addAnimation(this);
    }

    @Override
    protected void interpolate(double v) {
        double y = bombLet.getY() - speedY;
        double x = bombLet.getX() + speedX;
        speedY -= gravity;

        if (y > Game.HEIGHT) {
            bombLet.setY(Game.HEIGHT);
            bombLet.setX(x);
        } else if (y < 0) {
            bombLet.setY(0);
            bombLet.setX(x);
        } else if (x > Game.WIDTH) {
            bombLet.setX(Game.WIDTH);
            bombLet.setY(y);
        } else {
            bombLet.setY(y);
            bombLet.setX(x);
        }
        if (bombLet.getY() >= Game.HEIGHT - bombLet.HEIGHT - 180) {
            bombLet.setExploded(true);
            this.stop();
            for (int i = 0; i < game.getAllEnemyObjects().size(); i++) {
                if (game.getAllEnemyObjects().get(i).getX() < (bombLet.getX() + bombLet.WIDTH / 2) && (bombLet.getX() + bombLet.WIDTH / 2) < game.getAllEnemyObjects().get(i).getX() + game.getAllEnemyObjects().get(i).getWidth()) {
                    root.getChildren().remove(game.getAllEnemyObjects().get(i));
                    game.removeEnemyObject(game.getAllEnemyObjects().get(i));
                    break;
                }
            }
            pauseTransition(Duration.seconds(1.6), () -> {
                root.getChildren().remove(bombLet);
                game.removeMyObject(bombLet);
            });
        }
    }
    private void pauseTransition(Duration duration, Runnable callback) {
        PauseTransition pause = new PauseTransition(duration);
        pause.setOnFinished(event -> callback.run());
        pause.play();
    }
}
