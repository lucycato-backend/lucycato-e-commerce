package org.lucycato.mvc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommonRedisTemplate {
    private final RedisTemplate<String, String> redisTemplate;

    private final ObjectMapper objectMapper;

    public <T> T save(String key, T value) throws Exception {
        String jsonString = objectMapper.writeValueAsString(value);
        redisTemplate.opsForValue().set(key, jsonString);
        return value;
    }

    public <T> T find(String key, String value) throws Exception {
        String jsonString = redisTemplate.opsForValue().get(key);
        return objectMapper.readValue(jsonString, new TypeReference<T>() {});
    }

    public <T> T update(String key, T value) throws Exception {
        return save(key, value);
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }
}
