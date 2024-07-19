package com.example.spring_redis.controller;

import com.example.spring_redis.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class RedisController {

    private final RedisService redisService;

    @PostMapping("string")
    public Object setStrings(@RequestBody Map<String, String> map) {
        redisService.setStrings(map);
        return map;
    }

    @PostMapping
    public Object setObjects(@RequestBody Map<String, Object> map) {
        redisService.setObjects(map);

        return map;
    }

    @DeleteMapping
    public String deleteObject(@RequestParam String key) {
        redisService.deleteObject(key);
        return "delete 성공";
    }

    @GetMapping
    public Object getObject(@RequestParam String key) {
        return redisService.getObject(key);
    }

    @PostMapping("test")
    public void setTest(String key, String value) {
        redisService.setTest(key, value);
    }

    @GetMapping("search")
    public Object searchStrings(String keyPrefix) {
        return redisService.searchAllStrings(keyPrefix);
    }

    @PostMapping("expire")
    public Object setObjectAndExpire(@RequestParam String key, @RequestParam long time, @RequestBody Object value) {
        redisService.setObjectAndExpire(key, value, time);
        return value;
    }

    @PostMapping("testV2")
    public void setTestV2(String key, @RequestBody Object value) {
        redisService.setTestV2(key, value);
    }
}
