package br.com.fraudservice.infrastructure.rules;

import br.com.fraudservice.infrastructure.messaging.event.TransactionCreatedEvent;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Slf4j
public class TimeWindowRule implements FraudRule{

    private static final LocalTime MIDNIGHT = LocalTime.MIDNIGHT;
    private static final LocalTime FIVE_AM = LocalTime.of(5,0);

    @Override
    public boolean evaluate(TransactionCreatedEvent event) {
        log.info("Starting Time Window Rule Validation");
        var transactionTime = event.dateTime().toLocalTime();

        return transactionTime.equals(MIDNIGHT) || (transactionTime.isAfter(MIDNIGHT) && transactionTime.isBefore(FIVE_AM));
    }

    @Override
    public String getRuleName() {
        return "Time Window Rule";
    }
}
