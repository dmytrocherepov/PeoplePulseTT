package org.example.peoplepulsett.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Getter
@Setter
public class Cache {
    private static final int MAX_SIZE = 50;

    private Map<String, Object> cache = new HashMap<>();

    public void put(String key, Object value) {

        if (cache.size() >= MAX_SIZE) {
            String oldestKey = cache.keySet().iterator().next();
            cache.remove(oldestKey);
        }
        cache.put(key, value);
    }

}
