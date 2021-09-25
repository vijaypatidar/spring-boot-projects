package com.example.aop.services;

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

    public void deleteUser(String email) {
        users.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .map(users::remove);
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
