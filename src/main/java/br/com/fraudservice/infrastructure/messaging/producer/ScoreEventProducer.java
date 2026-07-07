package br.com.fraudservice.infrastructure.messaging.producer;

import br.com.fraudservice.domain.contract.ScoreEventPublisher;
import br.com.fraudservice.domain.enums.FraudScore;
import br.com.fraudservice.infrastructure.messaging.event.ScoreEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class ScoreEventProducer implements ScoreEventPublisher {

    private static final String TOPIC = "score.response";
    private final KafkaTemplate<String, ScoreEvent> kafkaTemplate;

    @Override
    public void publish(UUID transactionId, FraudScore score){

       var message = kafkaTemplate.send(TOPIC, transactionId.toString(), new ScoreEvent(transactionId,score));

        message.whenComplete((result, ex)->
        {
            if (ex != null){
                log.error("Event publish failed: {}", ex.getMessage());
            }
            else {
                log.info("Event published to Kafka → topic={} partition={} offset={}",
                        result.getRecordMetadata().topic(),
                        result.getRecordMetadata().partition(),
                        result.getRecordMetadata().offset());
            }
        });
    }
}
