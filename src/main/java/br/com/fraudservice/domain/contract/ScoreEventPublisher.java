package br.com.fraudservice.domain.contract;

import br.com.fraudservice.domain.enums.FraudScore;

import java.util.UUID;

public interface ScoreEventPublisher {
    void publish(UUID transactionId, FraudScore score);
}
