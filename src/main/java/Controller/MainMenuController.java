package Controller;

import Model.App;
import Model.Game;
import Model.User;
import View.Wave;
import javafx.scene.control.Alert;

import static View.Main.stage;

public class MainMenuController {
    public void startGame(User loggedInUser) throws Exception {
        Game game = new Game(loggedInUser, App.getDifficulty(), loggedInUser.getLastWave(), App.isIsMute(),App.isBlackAndWhite());
        Wave wave = new Wave();
        wave.setGame(game);
        wave.start(stage);
    }

    public void resumeGame() {
        // Resume the game
    }

    public void openProfileMenu() {
        // Open the profile menu
    }

    public void openScoreboard() {
        // Open the scoreboard
    }

    public void openSettingsMenu() {
        // Open the settings menu
    }

    public void exit() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("Are you sure you want to exit?");
        alert.showAndWait();
        if (alert.getResult().getText().equals("OK")) {
            //App.saveUsers(App.getUsers());
            App.saveApp(App.getGuestUserCount());
            stage.close();
        } else alert.close();
    }
}
