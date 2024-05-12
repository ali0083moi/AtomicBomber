package Controller;

import Model.App;
import Model.User;
import javafx.beans.binding.Bindings;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ScoreBoardController {
    private static ArrayList<User> users;

    public void showScoreBoard() {
        // Show the score board
    }

    public void sortScoreBoardByKills() {
        // Sort the score board by kills
    }

    public void sortScoreBoardByDifficulty() {
        // Sort the score board by difficulty
    }

    public void sortScoreBoardByAccuracy() {
        // Sort the score board by accuracy
    }

    public void back() {
        // Go back to the main menu
    }

    public void setScoreboardTable(TableView<User> scoreboardTable) {
        ArrayList<User> allUsers = new ArrayList<>(App.getUsers());
        allUsers.sort(Comparator.comparing(User::getLastWave).reversed());
        allUsers.sort(Comparator.comparing(User::getScore).reversed());

        // Limit the list to the top 10 users
        List<User> topUsers = allUsers.stream().limit(10).collect(Collectors.toList());

        // Create an ObservableList of users and add the top 10 users to it
        ObservableList<User> users = FXCollections.observableArrayList(topUsers);

        // Create TableColumn objects for each column in the table
        TableColumn<User, String> usernameColumn = new TableColumn<>("Username");
        TableColumn<User, Integer> scoreColumn = new TableColumn<>("Score");
        TableColumn<User, Integer> waveColumn = new TableColumn<>("Wave");
        TableColumn<User, Integer> killsColumn = new TableColumn<>("Kills");
        TableColumn<User, String> difficultyColumn = new TableColumn<>("Difficulty");
        TableColumn<User, Double> accuracyColumn = new TableColumn<>("Accuracy");

        // Set the cell value factory for each column to display the appropriate property of the User object
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        waveColumn.setCellValueFactory(new PropertyValueFactory<>("lastWave"));
        killsColumn.setCellValueFactory(new PropertyValueFactory<>("kills"));
        difficultyColumn.setCellValueFactory(new PropertyValueFactory<>("difficulty"));
        accuracyColumn.setCellValueFactory(new PropertyValueFactory<>("accuracy"));

        usernameColumn.setMinWidth(200);
        scoreColumn.setMinWidth(100);
        waveColumn.setMinWidth(100);
        killsColumn.setMinWidth(100);
        difficultyColumn.setMinWidth(100);
        accuracyColumn.setMinWidth(100);

        // Add the TableColumn objects to the TableView
        scoreboardTable.getColumns().setAll(usernameColumn, scoreColumn, waveColumn, killsColumn, difficultyColumn, accuracyColumn);

        // Set the items of the TableView to the ObservableList of users
        scoreboardTable.setItems(users);
        // Apply different styles to the first three rows to represent gold, silver, and bronze
        scoreboardTable.setRowFactory(tv -> new TableRow<User>() {
            @Override
            public void updateItem(User item, boolean empty) {
                super.updateItem(item, empty);
                if (getIndex() == 0 && item != null) {
                    setStyle("-fx-background-color: linear-gradient(to bottom, #fda949, #f06d39);");
                } else if (getIndex() == 1 && item != null) {
                    setStyle("-fx-background-color: linear-gradient(to bottom, #9b9fc0, #64678d);");
                } else if (getIndex() == 2 && item != null) {
                    setStyle("-fx-background-color: linear-gradient(to bottom, #40b0f2, #2b53c2);");
                } else {
                    setStyle("-fx-background-color: linear-gradient(to bottom, #ede8d9, #dcd3ba);");
                }
                setMinHeight(35);
            }
        });
    }
}