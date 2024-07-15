package com.example.spring_redis.controller;

import com.example.spring_redis.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class RedisController {

    private final RedisService redisService;

    @PostMapping
    public String setString(@RequestParam Map<String, String> map) {
        // map에 있는 모든 내용 리턴
        return map.toString();
    }

    @PostMapping("object")
    public Object setObject(String key, @RequestBody Object value) {
        // value의 모든 내용 리턴
        redisService.setObject(key, value);
        return value;
    }
}
