package br.com.fraudservice.infrastructure.messaging.event;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record TransactionCreatedEvent(
        UUID transactionId,
        UUID originAccountId,
        UUID targetAccountId,
        String type,
        String status,
        BigDecimal amount,
        LocalDateTime dateTime,
        String description
) {}