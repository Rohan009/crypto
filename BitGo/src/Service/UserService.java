package Service;

import model.User;

import java.util.HashMap;
import java.util.Map;

public class UserService {

    private static Map<String, User> users = new HashMap<>();

    public String getUserEmail(String userId) {
        if(users.containsKey(userId))
            return users.get(userId).getEmail();

        throw new IllegalArgumentException("invalid user");
    }

    public void addUser(User user) {
        if(users.containsKey(user.getUserId())) {
            throw new IllegalArgumentException("user already added");
        }
        users.put(user.getUserId(), user);
        System.out.println("User created: "+ user);
    }
}
