package View;

import Model.App;
import Model.User;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {
    public static Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        ArrayList<User> users = App.loadUsers();
        App.setUsers(users);
        App.loadApp();
        Main.stage = stage;
        RegisterMenu registerMenu = new RegisterMenu();
        registerMenu.start(stage);
        stage.setOnCloseRequest(event -> {
            App.saveUsers(App.getUsers());
            App.saveApp(App.getGuestUserCount());
//            App.saveApp(App.getGuestUserCount(), App.isIsMute());
        });
    }
}