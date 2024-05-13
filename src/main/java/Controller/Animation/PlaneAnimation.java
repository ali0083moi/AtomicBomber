package Controller.Animation;

import Model.Game;
import Model.Plane;
import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class PlaneAnimation extends Transition {
    private final Game game;
    private final Pane root;
    private final Plane plane;
    private final double speed = 1;
    private boolean up, down, left, right;
    private double angle = 0.0001;
    private boolean isFlipped = false;

    public PlaneAnimation(Game game, Pane root, Plane plane) {
        this.game = game;
        this.root = root;
        this.plane = plane;
        this.setCycleCount(-1);
        this.setCycleDuration(Duration.millis(100));
        game.addAnimation(this);
    }

    @Override
    protected void interpolate(double v) {
        plane.setAngle(angle);
        double piAngle = 2 * Math.PI;
        double dAngle = Math.PI / 180;
        plane.setRotate(-angle * 180 / Math.PI);
        if ((this.angle >= (piAngle / 4) && this.angle < (3 * piAngle / 4)) && !isFlipped) {
            isFlipped = true;
            plane.setFlipped(true);
            plane.setScaleY(-1);
        }
        if (((this.angle > 0 && this.angle < (piAngle / 4)) || (this.angle >= (3 * piAngle / 4) && this.angle < (piAngle))) && isFlipped) {
            isFlipped = false;
            plane.setFlipped(false);
            plane.setScaleY(1);
        }
        if (angle < 0) {
            this.angle = piAngle + this.angle;
        }
        else if (angle > piAngle) {
            this.angle = this.angle - piAngle;
        }

        double y = plane.getY() - speed * Math.sin(angle);
        double x = plane.getX() + speed * Math.cos(angle);

        if (isUp()) {
            if ((this.angle >= (piAngle / 4) && this.angle < (3 * piAngle / 4))) {
                angle -= dAngle;
            }
            if ((this.angle > 0 && this.angle < (piAngle / 4)) || (this.angle >= (3 * piAngle / 4) && this.angle < (piAngle))) {
                angle += dAngle;
            }
        }
        if (isDown()) {
            if ((this.angle >= (piAngle / 4) && this.angle < (3 * piAngle / 4))) {
                angle += dAngle;
            }
            if ((this.angle > 0 && this.angle < (piAngle / 4)) || (this.angle >= (3 * piAngle / 4) && this.angle < (piAngle))) {
                angle -= dAngle;
            }
        }
        if (isRight()) {
            if ((this.angle < (piAngle / 2) && this.angle >= 0)) {
                angle -= dAngle;
            }
            if ((this.angle >= (piAngle / 2) && this.angle < piAngle)) {
                angle += dAngle;
            }
        }
        if (isLeft()) {
            if ((this.angle >= 0 && this.angle < (piAngle / 2))) {
                angle += dAngle;
            }
            if ((this.angle >= (piAngle / 2) && this.angle < piAngle)) {
                angle -= dAngle;
            }
        }
        if (y <= 0) {
            this.angle = piAngle - this.angle;
            y = plane.getY();
        }
        if (x <= -plane.WIDTH) {
            x = root.getWidth();
        }
        else if (x >= root.getWidth()) {
            x = 0;
        }
        plane.setY(y);
        plane.setX(x);
        if (y > Game.HEIGHT - 180 - plane.HEIGHT) {
            System.out.println("Game Over!");
        }
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }
}
