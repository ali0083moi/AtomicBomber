package Model;

import Controller.Animation.PlaneAnimation;
import javafx.animation.Transition;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Objects;

public class Game {
    public static final int WIDTH = 900;
    public static final int HEIGHT = 600;
    public static final int GROUND_WIDTH = 900;
    public static final int GROUND_HEIGHT = 180;
    private Plane plane;
    private int kills;
    private int accuracy;
    private int clusterBombs;
    private int radioActiveBombs;
    private int wave;
    private int lives;
    private User user;
    private String difficulty;
    private int score;
    private int lastWave;
    private boolean isMuted;
    private boolean isBlackAndWhite;
    private ArrayList<Rectangle> allEnemyObjects = new ArrayList<>();
    private ArrayList<Rectangle> allMyObjects = new ArrayList<>();
    private ArrayList<Transition> animations = new ArrayList<>();

    public Game(User user, String difficulty, int lastWave, boolean isMuted, boolean isBlackAndWhite) {
        this.kills = 0;
        this.accuracy = 0;
        this.clusterBombs = 0;
        this.radioActiveBombs = 0;
        this.wave = 0;
        this.lives = 1;
        this.user = user;
        this.difficulty = difficulty;
        this.score = 0;
        this.lastWave = lastWave;
        this.isMuted = isMuted;
        this.isBlackAndWhite = isBlackAndWhite;
        user.setLastGame(this);
        //this.gameLauncher = new GameLauncher(this);
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public int getClusterBombs() {
        return clusterBombs;
    }

    public void setClusterBombs(int clusterBombs) {
        this.clusterBombs = clusterBombs;
    }

    public int getRadioActiveBombs() {
        return radioActiveBombs;
    }

    public void setRadioActiveBombs(int radioActiveBombs) {
        this.radioActiveBombs = radioActiveBombs;
    }

    public int getWave() {
        return wave;
    }

    public void setWave(int wave) {
        this.wave = wave;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addAnimation(PlaneAnimation planeAnimation) {
    }
    public void addEnemyObject(Rectangle object) {
        allEnemyObjects.add(object);
    }
    public void addMyObject(Rectangle object) {
        allMyObjects.add(object);
    }
    public void removeEnemyObject(Rectangle object) {
        allEnemyObjects.remove(object);
    }
    public void removeMyObject(Rectangle object) {
        allMyObjects.remove(object);
    }
    public void addAnimation(Transition transition) {
        animations.add(transition);
    }
    public void removeAnimation(Transition transition) {
        animations.remove(transition);
    }

    public ArrayList<Rectangle> getAllEnemyObjects() {
        return allEnemyObjects;
    }

    public ArrayList<Rectangle> getAllMyObjects() {
        return allMyObjects;
    }

    public ArrayList<Transition> getAnimations() {
        return animations;
    }

}
