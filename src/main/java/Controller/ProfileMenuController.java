package Controller;

import Model.App;
import Model.User;
import View.Main;
import View.RegisterMenu;
import javafx.scene.control.Alert;

public class ProfileMenuController {
    private final User loggedInUser = App.getLoggedInUser();

    public void changePassword(String currentPassword, String newPassword, String confirmPassword) {
        if (!loggedInUser.getPassword().equals(currentPassword)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid password");
            alert.setHeaderText("Incorrect password");
            alert.setContentText("Please enter a valid password");
            alert.show();
            return;
        }
        if (!newPassword.equals(confirmPassword)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid password");
            alert.setHeaderText("Passwords do not match");
            alert.setContentText("Please enter matching passwords");
            alert.show();
            return;
        }
        if (RegisterMenuController.passwordChecker(newPassword)) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Changing Password");
            alert.setHeaderText("Are you sure you want to change your password?");
            alert.setContentText("Your password will be changed.");
            alert.showAndWait();
            if (alert.getResult().getText().equals("OK")) {
                loggedInUser.setPassword(newPassword);
            } else {
                alert.close();
            }
        }
    }

    public boolean changeUsername(String newUsername) {
        if (RegisterMenuController.usernameChecker(newUsername)) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Changing Username");
            alert.setHeaderText("Are you sure you want to change your username?");
            alert.setContentText("Your new username will be: " + newUsername);
            alert.showAndWait();
            if (alert.getResult().getText().equals("OK")) {
                loggedInUser.setUsername(newUsername);
                return true;
            } else {
                alert.close();
                return false;
            }
        }
        return false;
    }

    public void deleteAccount() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Deleting Account");
        alert.setHeaderText("Are you sure you want to delete your account?");
        alert.setContentText("Your account will be deleted.");
        alert.showAndWait();
        if (alert.getResult().getText().equals("OK")) {
            App.deleteUser(loggedInUser);
            logout();
        } else {
            alert.close();
        }
    }

    public void logout() {
        App.setLoggedInUser(null);
        RegisterMenu registerMenu = new RegisterMenu();
        try {
            registerMenu.start(Main.stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openAvatarMenu() {
    }
}
