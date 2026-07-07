package br.com.fraudservice.infrastructure.messaging.consumer;

import br.com.fraudservice.application.usecase.EvaluateFraudUseCase;
import br.com.fraudservice.infrastructure.messaging.event.TransactionCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class TransactionEventConsumer {

    private final EvaluateFraudUseCase evaluateFraudUseCase;

    @KafkaListener(topics = "transaction.created", groupId = "transaction-group", containerFactory = "KafkaListenerContainerFactory")
    public void consume(@Payload TransactionCreatedEvent event,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                        @Header(KafkaHeaders.OFFSET) long offset,
                        Acknowledgment ack){

        log.info("Event received from Kafka. Partition={} offset={} transactionId={}", partition, offset, event.transactionId());

        try {
            evaluateFraudUseCase.execute(event);
            ack.acknowledge();
            log.info("Score sent to Transaction-Service. transactionId={}", event.transactionId());
        } catch (Exception e) {
            log.error("Failed to process event. transactionId={}", event.transactionId(), e);
            throw new KafkaException("Error while processing event");
        }
    }

}
