package br.com.fraudservice.infrastructure.rules;


import br.com.fraudservice.domain.FraudResult;
import br.com.fraudservice.domain.enums.FraudScore;
import br.com.fraudservice.infrastructure.messaging.event.TransactionCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class FraudRuleEngine {

    private final List<FraudRule> rules;

    public FraudResult checkScore(TransactionCreatedEvent event) {

        FraudScore score;

        List<String> triggeredRules = rules.stream()
                .filter(rule -> rule.evaluate(event))
                .map(FraudRule::getRuleName)
                .toList();

        log.info("List of the triggered rules ={}", triggeredRules);

        switch (triggeredRules.size()){
            case 0 -> score = FraudScore.LOW;
            case 1 -> score = FraudScore.MEDIUM;
            case 2 -> score = FraudScore.HIGH;
            default -> score = FraudScore.HIGH;
        }

        return new FraudResult(score, triggeredRules);
    }
}

