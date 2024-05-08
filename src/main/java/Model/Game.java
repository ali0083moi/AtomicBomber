package Model;

public class Game {
    private int kills;
    private int accuracy;
    private int clusterBombs;
    private int radioActiveBombs;
    private int wave;
    private int lives;

    public Game() {
        this.kills = 0;
        this.accuracy = 0;
        this.clusterBombs = 0;
        this.radioActiveBombs = 0;
        this.wave = 0;
        this.lives = 1;
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
}
