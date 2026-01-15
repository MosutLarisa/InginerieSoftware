package core;

import java.util.*;
import model.User;

public class UserManager {
    private static UserManager instance;
    private Map<String, User> users;

    private UserManager() {
        this.users = new HashMap<>();
    }

    public static synchronized UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    public User registerUser(String username, String password, double initialBalance) {
        if (users.containsKey(username)) {
            return null;
        }
        User user = new User(username, password, initialBalance);
        users.put(username, user);
        return user;
    }

    public User login(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public User getUser(String username) {
        return users.get(username);
    }
}