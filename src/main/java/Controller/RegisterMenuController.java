package Controller;

import Enums.RegexChecker;
import Model.App;
import Model.User;
import View.Main;
import View.MainMenu;
import javafx.scene.control.Alert;

import java.util.regex.Matcher;

public class RegisterMenuController {

    Matcher matcher;
    public void login(String username, String password) throws Exception {
        User user = App.getUserByUsername(username);
        if (user == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid username");
            alert.setHeaderText("Username does not exist");
            alert.setContentText("Please enter a valid username");
            alert.show();
            return;
        }
        if (!user.getPassword().equals(password)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid password");
            alert.setHeaderText("Incorrect password");
            alert.setContentText("Please enter a valid password");
            alert.show();
            return;
        }
        App.setLoggedInUser(user);
        MainMenu mainMenu = new MainMenu();
        mainMenu.start(Main.stage);
    }

    public void signup(String username, String password) throws Exception {
        if (username.length() < 4 || username.length() > 20){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid username");
            alert.setHeaderText("Not enough characters");
            alert.setContentText("Username must be between 4 and 20 characters");
            alert.show();
            return;
        }
        if ((matcher = RegexChecker.USERNAME_CHECK.getMatherMatches(username)) == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid username");
            alert.setHeaderText("Invalid username format");
            alert.setContentText("Please only use English characters and _");
            alert.show();
            return;
        }
        if (App.getUserByUsername(username) != null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid username");
            alert.setHeaderText("Username already exists");
            alert.setContentText("Please choose another username");
            alert.show();
            return;
        }
        if (password.length() < 6 || password.length() > 20){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid password");
            alert.setHeaderText("Not enough characters");
            alert.setContentText("Password must be between 6 and 20 characters");
            alert.show();
            return;
        }
        if ((matcher = RegexChecker.PASSWORD_CHECK.getMatherMatches(password)) == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid password");
            alert.setHeaderText("Invalid password format");
            alert.setContentText("Password must contain at least one of these characters: @#$^&!\n" +
                    "And only use English characters");
            alert.show();
            return;
        }
        User user = new User(username, password);
        App.addUser(user);
        App.setLoggedInUser(user);
        MainMenu mainMenu = new MainMenu();
//        showLoader(Main.stage);
        mainMenu.start(Main.stage);
    }
//    private void showLoader(Stage primaryStage) throws Exception {
//        // Load the loader screen
//        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/FXML/Loader.fxml"));
//        StackPane loaderRoot = FXMLLoader.load(Main.class.getResource("/FXML/Loader.fxml"));
//        LoaderController loaderController = loader.getController();
//
//        // Show the loader screen as a separate stage
//        Scene loaderScene = new Scene(loaderRoot);
//        primaryStage.setScene(loaderScene);
//        primaryStage.show();
//
//        // Simulate loading process
//        Thread loadingThread = new Thread(() -> {
//            try {
//                for (double progress = 0; progress < 1; progress += 0.2) {
//                    // Update progress bar
//                    loaderController.updateProgress(progress);
//                    // Simulate loading time
//                    Thread.sleep(100);
//                }
//                // Close loader screen when loading is complete
//                primaryStage.close();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        loadingThread.start();
//    }
}
