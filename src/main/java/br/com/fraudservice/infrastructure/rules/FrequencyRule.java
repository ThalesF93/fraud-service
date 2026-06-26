package br.com.fraudservice.infrastructure.rules;

import br.com.fraudservice.infrastructure.messaging.event.TransactionCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;

@Slf4j
@RequiredArgsConstructor
public class FrequencyRule implements FraudRule {

    private final RedisTemplate<String, Long> redisTemplate;
    private static final int THRESHOLD = 3;

    @Override
    public boolean evaluate(TransactionCreatedEvent event) {
        log.info("Starting Repetition Rule Validation");

        String cacheKey = "frequency:%s" + event.originAccountId();

        Long count = redisTemplate.opsForValue().increment(cacheKey);

        if (count == 1){
            redisTemplate.expire(cacheKey, Duration.ofMinutes(10));
        }

        return count >= THRESHOLD;
    }

    @Override
    public String getRuleName() {
        return "Frequency Rule";
    }
}
