package com.example.aop.services;

import com.example.aop.apsect.Loggable;
import com.example.aop.models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final List<User> users = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
    }
    @Loggable(description = "Delete")
    public void deleteUser(String email) {
        users.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .map(users::remove);
        del();
    }

    @Loggable(description = "delll")
    public void del() {

    }

    public List<User> getUsers() {
        return new ArrayList<>(users);
    }


    public User getUser(String email) {
        return users.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElseThrow();
    }
}
