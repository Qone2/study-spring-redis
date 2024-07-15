package com.example.spring_redis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    private final StringRedisTemplate stringRedisTemplate;

    public void setObjects(Map<String, Object> map) {
        redisTemplate.opsForValue().multiSet(map);
    }

    public void setObject(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void setStrings(Map<String, String> map) {
        stringRedisTemplate.opsForValue().multiSet(map);
    }

    public void setString(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    public Object getObject(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public Object getAllTests(String keyPrefix) {
        Set<String> keys = stringRedisTemplate.keys(keyPrefix + "-*");
        Map<String, String> keyValue = new LinkedHashMap<>();
        assert keys != null;
        keys.forEach(key -> keyValue.put(key, stringRedisTemplate.opsForValue().get(key)));
        return keyValue;
    }


}
