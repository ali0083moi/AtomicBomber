package Model;

public class User {
    private String username;
    private String password;
    private String avatar;
    private int score;
    private int lastWave;
    private int gamesPlayed;
    private int gamesWon;
    private int gamesLost;
    private int kills;
    private int deaths;
    private int accuracy;
    private Game lastGame;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.avatar = "default";
        this.score = 0;
        this.lastWave = 0;
        this.gamesPlayed = 0;
        this.gamesWon = 0;
        this.gamesLost = 0;
        this.kills = 0;
        this.deaths = 0;
        this.accuracy = 0;
        this.lastGame = null;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLastWave() {
        return lastWave;
    }

    public void setLastWave(int lastWave) {
        this.lastWave = lastWave;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    public int getGamesLost() {
        return gamesLost;
    }

    public void setGamesLost(int gamesLost) {
        this.gamesLost = gamesLost;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public Game getLastGame() {
        return lastGame;
    }

    public void setLastGame(Game lastGame) {
        this.lastGame = lastGame;
    }
}
