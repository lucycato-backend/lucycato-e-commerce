package org.lucycato.mvc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.lucycato.common.error.ErrorCodeImpl;
import org.lucycato.common.exception.ApiExceptionImpl;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class CommonRedisTemplate {
    private final RedisTemplate<String, String> redisTemplate;

    private final ObjectMapper objectMapper;

    public <T> T save(String key, T value, Long ttl, TimeUnit timeUnit) throws Exception {
        String jsonString = objectMapper.writeValueAsString(value);
        redisTemplate.opsForValue().set(key, jsonString, ttl, timeUnit);
        return value;
    }

    public <T> T find(String key) throws Exception {
        String jsonString = redisTemplate.opsForValue().get(key);
        return objectMapper.readValue(jsonString, new TypeReference<T>() {
        });
    }

    public <T> List<T> findAll(List<String> keys) throws Exception {
        List<String> jsonStringList = redisTemplate.opsForValue().multiGet(keys);
        return jsonStringList.stream().map(jsonString -> {
            try {
                return objectMapper.readValue(jsonString, new TypeReference<T>() {
                });
            } catch (Exception e) {
                throw new ApiExceptionImpl(ErrorCodeImpl.JSON_PARSING);
            }
        }).toList();
    }

    public <T> T update(String key, T value, Long ttl, TimeUnit timeUnit) throws Exception {
        return save(key, value, ttl, timeUnit);
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }
}
