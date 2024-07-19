package com.example.spring_redis.service;

import com.example.spring_redis.config.TestRedisSerializer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final String TestPrefix = "TEST-";
    private final DateTimeFormatter TestSuffix = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    @Qualifier("redisTemplate")
    private final RedisTemplate<String, Object> redisTemplate;

    private final StringRedisTemplate stringRedisTemplate;

    @Qualifier("testRedisTemplate")
    private final RedisTemplate<String, Object> testRedisTemplate;

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

    public void setTest(String key, Object value) {
        setObject(TestPrefix + key + LocalDateTime.now().format(TestSuffix), value);
    }

    public void setTestV2(String key, Object value) {
        testRedisTemplate.opsForValue().set(key, value);
    }

    public Object searchAllStrings(String keyPrefix) {
        Map<String, Object> keyValues = new LinkedHashMap<>();
        Set<String> keys = redisTemplate.keys(keyPrefix + "-*");
        assert keys != null;
        ArrayList<String> keyList = new ArrayList<>(keys);
        List<Object> values = redisTemplate.opsForValue().multiGet(keyList);
        for (int i = 0; i < keyList.size(); i++) {
            assert values != null;
            keyValues.put(keyList.get(i), values.get(i));
        }
        return keyValues;
    }

    public void deleteObject(String key) {
        redisTemplate.opsForValue().getAndDelete(key);
    }

    public void setObjectAndExpire(String key, Object value, long timeSeconds) {
        redisTemplate.opsForValue().set(key, value, timeSeconds, TimeUnit.SECONDS);
    }


}
