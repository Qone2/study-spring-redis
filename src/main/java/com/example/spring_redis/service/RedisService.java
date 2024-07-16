package com.example.spring_redis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final String TestPrefix = "TEST-";
    private final DateTimeFormatter TestSuffix = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

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

    public void setTest(String key, String value) {
        setString(TestPrefix + key + LocalDateTime.now().format(TestSuffix), value);
    }

    public Object searchAllStrings(String keyPrefix) {
        Set<String> keys = stringRedisTemplate.keys(keyPrefix + "-*");
        Map<String, String> keyValue = new LinkedHashMap<>();
        assert keys != null;
        keys.forEach(key -> keyValue.put(key, stringRedisTemplate.opsForValue().get(key)));
        return keyValue;
    }

    public void deleteObject(String key) {
        redisTemplate.opsForValue().getAndDelete(key);
    }


}
