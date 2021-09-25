package com.example.aop.controllers;

import com.example.aop.apsect.Loggable;
import com.example.aop.models.User;
import com.example.aop.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/users")
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Loggable(description = "New user creation request")
    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody User user) {
        userService.addUser(user);
        return ResponseEntity.ok("User add " + user.getName());
    }

    @GetMapping("/{email}")
    public User getUserById(@PathVariable String email) {
        return userService.getUser(email);
    }

    @GetMapping()
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @Loggable(description = "User delete request")
    @DeleteMapping("/{email}")
    public ResponseEntity<String> deleteUser(@PathVariable String email) {
        userService.deleteUser(email);
        return ResponseEntity.ok("User deleted " + email);
    }

}
