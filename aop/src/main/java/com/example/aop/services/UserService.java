package com.example.aop.services;

import com.example.aop.apsect.cache.Cache;
import com.example.aop.apsect.cache.ClearCache;
import com.example.aop.apsect.logger.Log;
import com.example.aop.apsect.retryable.Retry;
import com.example.aop.models.User;
import java.util.ArrayList;
import java.util.List;
import javax.management.ServiceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
    private final List<User> users = new ArrayList<>();
    int count = 0;

    public void addUser(User user) {
        users.add(user);
    }

    @Log(description = "Delete")
    @ClearCache(key = "users.email")
    public void deleteUser(String email) {
        users.removeIf(user -> user.getEmail().equals(email));
    }

    @Retry(retryCount = 3, exception = ServiceNotFoundException.class)
    public List<User> getUsers() throws ServiceNotFoundException {
        count++;
        if (count % 3 == 0) {
            throw new ServiceNotFoundException("UserService is not available");
        }
        return new ArrayList<>(users);
    }


    @Cache(key = "users.email")
    public User getUser(String email) {
        return users.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElseThrow();
    }
}
