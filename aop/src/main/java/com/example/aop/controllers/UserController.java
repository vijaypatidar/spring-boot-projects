package com.example.aop.controllers;

import com.example.aop.apsect.logger.Log;
import com.example.aop.apsect.timed.Timed;
import com.example.aop.models.User;
import com.example.aop.services.UserService;
import java.util.List;
import javax.management.ServiceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @Timed(description = "users.add.user")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        userService.addUser(user);
        return ResponseEntity.ok("User add " + user.getName());
    }

    @GetMapping("/{email}")
    @Timed(description = "users.getBy.email")
    public User getUserById(@PathVariable String email) {
        return userService.getUser(email);
    }

    @GetMapping()
    @Timed(description = "users.get.user")
    public List<User> getUsers() throws ServiceNotFoundException {
        return userService.getUsers();
    }

    @Log(description = "User delete request")
    @DeleteMapping("/{email}")
    public ResponseEntity<String> deleteUser(@PathVariable String email) {
        userService.deleteUser(email);
        return ResponseEntity.ok("User deleted " + email);
    }

}
