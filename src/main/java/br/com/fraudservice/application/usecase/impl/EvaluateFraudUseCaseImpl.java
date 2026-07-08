package br.com.fraudservice.application.usecase.impl;

import br.com.fraudservice.application.usecase.EvaluateFraudUseCase;
import br.com.fraudservice.domain.contract.ScoreEventPublisher;
import br.com.fraudservice.domain.entity.FraudAlert;
import br.com.fraudservice.domain.enums.FraudStatus;
import br.com.fraudservice.domain.repository.FraudRepository;
import br.com.fraudservice.infrastructure.messaging.event.TransactionCreatedEvent;
import br.com.fraudservice.infrastructure.rules.FraudRuleEngine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class EvaluateFraudUseCaseImpl implements EvaluateFraudUseCase {

    private final FraudRuleEngine engine;
    private final FraudRepository repository;
    private final ScoreEventPublisher eventPublisher;

    @Override
    public void execute(TransactionCreatedEvent event) {

        var result = engine.checkScore(event);

        var fraudAlert = FraudAlert.builder()
                .transactionId(event.transactionId())
                .accountId(event.originAccountId())
                .score(result.score())
                .status(FraudStatus.ON_REVIEW)
                .ruleTriggered(result.triggeredRules().toString())
                .build();

        repository.save(fraudAlert);

        eventPublisher.publish(event.transactionId(), fraudAlert.getScore());

    }
}
