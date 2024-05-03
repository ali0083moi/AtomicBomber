package Model;

import java.util.ArrayList;

public class App {
    private static User loggedInUser;
    private static ArrayList<User> users = new ArrayList<User>();
    private static int guestUserCount = 0;
    private static boolean isMute = false;

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
}
