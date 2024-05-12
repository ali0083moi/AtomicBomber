package Model;

import java.io.File;
import java.util.ArrayList;

import Enums.Data;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class App {
    private static User loggedInUser;
    private static ArrayList<User> users = new ArrayList<User>();
    private static ArrayList<User> scoreBoardUsers = new ArrayList<User>();
    private static int guestUserCount;
    private static boolean isMute;
    private static final String USERS_JSON_FILE = "src/main/DB/users.json";
    private static String difficulty = Data.EASY.getValue();
    private static boolean isBlackAndWhite = false;
    private static boolean isArrowKeys = true;

    public App(int guestUserCount, boolean isMute) {
        this.guestUserCount = guestUserCount;
        this.isMute = isMute;
    }

    public static void saveApp(int guestUserCount) {
        File file = new File("src/main/DB/appData.txt");
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(String.valueOf(guestUserCount));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadApp() {
        File file = new File("src/main/DB/appData.txt");
        try {
            FileReader reader = new FileReader(file);
            Scanner scanner = new Scanner(file);
            App.guestUserCount = scanner.nextInt();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public static void saveUsers(ArrayList<User> users) {
//        try {
//            FileWriter writer = new FileWriter(USERS_JSON_FILE);
//            Gson gson = new GsonBuilder().setPrettyPrinting().create();
//            gson.toJson(users, writer);
//            writer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//    public static ArrayList<User> loadUsers() {
//        ArrayList<User> users = new ArrayList<>();
//        try {
//            FileReader reader = new FileReader(USERS_JSON_FILE);
//            Gson gson = new Gson();
//            User[] userArray = gson.fromJson(reader, User[].class);
//            if (userArray != null) {
//                for (User user : userArray) {
//                    users.add(user);
//                }
//            }
//            reader.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return users;
//    }
    public static User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public static void addUser(User user) {
        users.add(user);
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User loggedInUser) {
        App.loggedInUser = loggedInUser;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static void setUsers(ArrayList<User> users) {
        App.users = users;
    }

    public static int getGuestUserCount() {
        return guestUserCount;
    }

    public static void setGuestUserCount(int guestUserCount) {
        App.guestUserCount = guestUserCount;
    }

    public static boolean isIsMute() {
        return isMute;
    }

    public static void setIsMute(boolean isMute) {
        App.isMute = isMute;
    }

    public static void deleteUser(User loggedInUser) {
        users.remove(loggedInUser);
    }

    public static String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public static boolean isBlackAndWhite() {
        return isBlackAndWhite;
    }

    public void setBlackAndWhite(boolean blackAndWhite) {
        isBlackAndWhite = blackAndWhite;
    }

    public static boolean isArrowKeys() {
        return isArrowKeys;
    }

    public void setArrowKeys(boolean arrowKeys) {
        isArrowKeys = arrowKeys;
    }
}
