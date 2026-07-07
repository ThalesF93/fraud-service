package br.com.fraudservice.application.usecase;

import br.com.fraudservice.infrastructure.messaging.event.TransactionCreatedEvent;

public interface EvaluateFraudUseCase {

    void execute(TransactionCreatedEvent event);
}
