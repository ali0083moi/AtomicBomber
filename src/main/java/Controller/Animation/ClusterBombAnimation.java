package Controller.Animation;

import Model.*;
import javafx.animation.PauseTransition;
import javafx.animation.Transition;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.util.Timer;
import java.util.TimerTask;

public class ClusterBombAnimation extends Transition {
    private final Game game;
    private final Pane root;
    private final ClusterBomb clusterBomb;
    private final Plane plane;
    private double speedX;
    private double speedY;
    private final double gravity = 0.02;
    private double angle;
    private boolean isFlipped = false;
    private MediaPlayer mediaPlayer;

    public ClusterBombAnimation(Game game, Pane root, ClusterBomb clusterBomb, Plane plane) {
        this.game = game;
        this.root = root;
        this.clusterBomb = clusterBomb;
        this.plane = plane;
        angle = plane.getAngle();
        isFlipped = plane.isFlipped();
        speedX = 1.5 * Math.cos(Math.toRadians(angle));
        speedY = Math.sin(Math.toRadians(angle));
        if (isFlipped) {
            speedY = -speedY;
            speedX = -speedX;
            clusterBomb.setRotate(-angle * 180 / Math.toDegrees(Math.PI) + 15);
        } else {
            clusterBomb.setRotate(-angle * 180 / Math.toDegrees(Math.PI) - 15);
        }
        this.setCycleCount(-1);
        this.setCycleDuration(Duration.millis(100));
        game.addAnimation(this);
        Media media = new Media(getClass().getResource("/sounds/bomb-drop.wav").toExternalForm());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    @Override
    protected void interpolate(double v) {
        double y = clusterBomb.getY() - speedY;
        double x = clusterBomb.getX() + speedX;
        speedY -= gravity;

        if (y > Game.HEIGHT) {
            clusterBomb.setY(Game.HEIGHT);
            clusterBomb.setX(x);
        } else if (y < 0) {
            clusterBomb.setY(0);
            clusterBomb.setX(x);
        } else if (x > Game.WIDTH) {
            clusterBomb.setX(Game.WIDTH);
            clusterBomb.setY(y);
        } else {
            clusterBomb.setY(y);
            clusterBomb.setX(x);
        }
        if (speedY <= -2) {
            this.stop();
            for (int i = 0; i < 5; i++) {
                BombLet bombLet = new BombLet(game, clusterBomb.getX(), clusterBomb.getY());
                BombLetAnimation bombLetAnimation = new BombLetAnimation(game, root, bombLet, plane);
                bombLetAnimation.play();
                game.addMyObject(bombLet);
                root.getChildren().add(bombLet);
            }
            root.getChildren().remove(clusterBomb);
            game.removeMyObject(clusterBomb);
        } else if (clusterBomb.getY() >= Game.HEIGHT - clusterBomb.HEIGHT - 180) {
            mediaPlayer.stop();
            Media media = new Media(getClass().getResource("/sounds/bomb-explosion.mp3").toExternalForm());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
            clusterBomb.setExploded(true);
            this.stop();
            for (int i = 0; i < game.getAllEnemyObjects().size(); i++) {
                if (game.getAllEnemyObjects().get(i).getX() < (clusterBomb.getX() + clusterBomb.WIDTH / 2) && (clusterBomb.getX() + clusterBomb.WIDTH / 2) < game.getAllEnemyObjects().get(i).getX() + game.getAllEnemyObjects().get(i).getWidth()) {
                    root.getChildren().remove(game.getAllEnemyObjects().get(i));
                    game.removeEnemyObject(game.getAllEnemyObjects().get(i));
                    break;
                }
            }
            pauseTransition(Duration.seconds(1.2), () -> {
                root.getChildren().remove(clusterBomb);
                game.removeMyObject(clusterBomb);
            });
        }
    }
    private void pauseTransition(Duration duration, Runnable callback) {
        PauseTransition pause = new PauseTransition(duration);
        pause.setOnFinished(event -> callback.run());
        pause.play();
    }
}
