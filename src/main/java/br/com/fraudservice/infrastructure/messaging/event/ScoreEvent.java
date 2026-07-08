package br.com.fraudservice.infrastructure.messaging.event;

import br.com.fraudservice.domain.enums.FraudScore;

import java.util.UUID;

public record ScoreEvent(

        UUID transactionId,

        FraudScore score
) {
}
