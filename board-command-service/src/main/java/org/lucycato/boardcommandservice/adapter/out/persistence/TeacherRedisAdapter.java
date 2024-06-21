package org.lucycato.boardcommandservice.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.lucycato.boardcommandservice.application.port.out.TeacherRedisPort;
import org.lucycato.common.annotation.hexagonal.out.PersistenceAdapter;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;

import java.util.*;
import java.util.stream.Collectors;
@PersistenceAdapter
@RequiredArgsConstructor
public class TeacherRedisAdapter implements TeacherRedisPort {

    private final RedisTemplate<String, Object> redisTemplate;
    private static final String TEACHER_NOTICE_KEY = "teacherNotice:";
    private static final long TTL = 7 * 24 * 60 * 60; // 1주일을 초로 계산
    @Override
    public void addTeacherNoticeRedis(Long teacherId, Long noticeId) {
        String key = TEACHER_NOTICE_KEY + teacherId + ":" + noticeId;
        redisTemplate.opsForValue().set(key, noticeId, Duration.ofSeconds(TTL));
    }

    @Override
    public List<Long> getTeacherNotices(Long teacherId) {
        String pattern = TEACHER_NOTICE_KEY + teacherId + ":*";
        Set<String> keys = redisTemplate.keys(pattern);
        if (keys == null) {
            return Collections.emptyList();
        }
        return keys.stream()
                .map(key -> ((Number) redisTemplate.opsForValue().get(key)).longValue())
                .sorted()
                .collect(Collectors.toList());
    }




}
