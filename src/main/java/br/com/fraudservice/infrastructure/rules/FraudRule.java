package br.com.fraudservice.infrastructure.rules;

import br.com.fraudservice.infrastructure.messaging.event.TransactionCreatedEvent;

public interface FraudRule {
    boolean evaluate(TransactionCreatedEvent event);
    String getRuleName();
}
