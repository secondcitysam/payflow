package com.payflow.switchservice.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final StringRedisTemplate redisTemplate;

    public boolean acquireIdempotencyLock(
            String key
    ) {

        Boolean success =
                redisTemplate
                        .opsForValue()
                        .setIfAbsent(
                                key,
                                "PROCESSING",
                                Duration.ofHours(24)
                        );

        return Boolean.TRUE.equals(
                success
        );
    }

    public void saveIdempotencyKey(
            String key,
            String transactionReference
    ) {

        redisTemplate.opsForValue().set(
                key,
                transactionReference,
                Duration.ofHours(24)
        );
    }

    public String getTransactionReference(
            String key
    ) {

        return redisTemplate
                .opsForValue()
                .get(key);
    }

    public boolean exists(
            String key
    ) {

        return Boolean.TRUE.equals(
                redisTemplate.hasKey(key)
        );
    }

    public Long incrementTransactionCount(
            String key
    ) {

        return redisTemplate
                .opsForValue()
                .increment(key);
    }

    public void setExpiry(
            String key,
            Duration duration
    ) {

        redisTemplate.expire(
                key,
                duration
        );
    }
}