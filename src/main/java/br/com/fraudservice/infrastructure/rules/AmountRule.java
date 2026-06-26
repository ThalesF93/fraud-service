package br.com.fraudservice.infrastructure.rules;

import br.com.fraudservice.infrastructure.messaging.event.TransactionCreatedEvent;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
public class AmountRule implements FraudRule {

    private static final BigDecimal MAX_VALUE = new BigDecimal("5000");

    @Override
    public boolean evaluate(TransactionCreatedEvent event) {
        log.info("Starting Amount Rule Validation");
        var transactionValue = event.amount();

        return transactionValue.compareTo(MAX_VALUE) >=0;
    }

    @Override
    public String getRuleName() {
        return "Amount rule";
    }
}
