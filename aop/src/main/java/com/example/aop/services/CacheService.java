package com.example.aop.services;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class CacheService {
    private final Map<String, Object> map = new HashMap<>();

    public Object get(String key) {
        return map.get(key);
    }

    public void put(String key, Object res) {
        map.put(key,res);
    }

    public void remove(String key) {
        map.remove(key);
    }
}
