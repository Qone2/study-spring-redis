package com.example.spring_redis.controller;

import com.example.spring_redis.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class RedisController {

    private final String TestPrefix = "TEST-";
    private final DateTimeFormatter TestSuffix = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    private final RedisService redisService;

    @PostMapping("string/multi")
    public Object setStrings(@RequestBody Map<String, String> map) {
        // map에 있는 모든 내용 리턴
        redisService.setStrings(map);
        return map;
    }

    @PostMapping
    public Object setObjects(@RequestBody Map<String, Object> map) {
        // map의 모든 내용을 저장
        redisService.setObjects(map);

        return map;
    }

    @GetMapping
    public Object getObject(@RequestParam String key) {
        return redisService.getObject(key);
    }

    @PostMapping("test")
    public void setTest(String key, String value) {
        redisService.setString(TestPrefix + key + LocalDateTime.now().format(TestSuffix), value);
    }

    @GetMapping("search")
    public Object getTests(String keyPrefix) {
        return redisService.getAllTests(keyPrefix);
    }
}
