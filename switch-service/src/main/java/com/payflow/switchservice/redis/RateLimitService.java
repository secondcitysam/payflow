package com.payflow.switchservice.redis;

import com.payflow.switchservice.exception.RateLimitExceededException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class RateLimitService {

    private final RedisService redisService;

    private static final int MAX_REQUESTS = 5;

    public void validate(
            String senderAccount
    ) {

        String redisKey =
                "rate_limit:" + senderAccount;

        Long count =
                redisService.incrementTransactionCount(
                        redisKey
                );

        if(count != null && count == 1)
        {
            redisService.setExpiry(
                    redisKey,
                    Duration.ofMinutes(1)
            );
        }

        if(count != null &&
                count > MAX_REQUESTS)
        {
            throw new RateLimitExceededException(
                    "Maximum 5 transactions per minute allowed"
            );
        }
    }
}