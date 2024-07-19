package com.example.spring_redis.config;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class TestRedisSerializer implements RedisSerializer<String> {

    private final String testPrefix = "TEST-";
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    @Override
    public byte[] serialize(String string) throws SerializationException {
        return string == null ? null : (testPrefix + string + LocalDateTime.now().format(dateTimeFormatter)).getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public String deserialize(byte[] bytes) throws SerializationException {
        return bytes == null ? null : new String(bytes, StandardCharsets.UTF_8);
    }
}
