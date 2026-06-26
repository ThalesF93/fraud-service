package br.com.fraudservice.infrastructure.rules;

import br.com.fraudservice.infrastructure.messaging.event.TransactionCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;

@Slf4j
@RequiredArgsConstructor
public class RepetitionRule implements FraudRule{

    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean evaluate(TransactionCreatedEvent event) {
        log.info("Starting Repetition Rule Validation");

        String cacheKey = String.format("repetition:%s:%s:%s", event.originAccountId().toString(), event.targetAccountId().toString(), event.amount().toString());
        String cached = redisTemplate.opsForValue().get(cacheKey);

        if (cached != null) {
            return true;
        }
        redisTemplate.opsForValue().set(cacheKey, "true", Duration.ofMinutes(2));

        return false;
    }

    @Override
    public String getRuleName() {
        return "Repetition Rule";
    }
}
