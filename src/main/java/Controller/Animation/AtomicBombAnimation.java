package Controller.Animation;

import Model.AtomicBomb;
import Model.Bomb;
import Model.Game;
import Model.Plane;
import javafx.animation.PauseTransition;
import javafx.animation.Transition;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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
    private MediaPlayer mediaPlayer;

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
        Media media = new Media(getClass().getResource("/sounds/atomic-bomb-drop.mp3").toExternalForm());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
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
            mediaPlayer.stop();
            Media media = new Media(getClass().getResource("/sounds/atomic-bomb-explosion.mp3").toExternalForm());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
            for (int i = 0; i < game.getAllEnemyObjects().size(); i++) {
                if (game.getAllEnemyObjects().get(i).getX() < (atomicBomb.getX() + atomicBomb.WIDTH / 2) && (atomicBomb.getX() + atomicBomb.WIDTH / 2) < game.getAllEnemyObjects().get(i).getX() + game.getAllEnemyObjects().get(i).getWidth()) {
                    root.getChildren().remove(game.getAllEnemyObjects().get(i));
                    game.removeEnemyObject(game.getAllEnemyObjects().get(i));
                    break;
                }
            }
            pauseTransition(Duration.seconds(1.8), () -> {
                root.getChildren().remove(atomicBomb);
                game.removeMyObject(atomicBomb);
            });
        }
    }
    private void pauseTransition(Duration duration, Runnable callback) {
        PauseTransition pause = new PauseTransition(duration);
        pause.setOnFinished(event -> callback.run());
        pause.play();
    }
}
