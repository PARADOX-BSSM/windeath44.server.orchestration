package windeath44.orchestration.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import windeath44.orchestration.domain.model.type.Topic;
import windeath44.orchestration.global.kafka.KafkaProducer;
import windeath44.server.memorial.avro.MemorialBowedAvroSchema;

@Service
@RequiredArgsConstructor
public class RemainTokenIncreaseService {
    private final KafkaProducer kafkaProducer;

    public void execute(MemorialBowedAvroSchema memorialBowedAvroSchema) {
        String topic = Topic.REMAIN_TOKEN_INCREASE.getTopicName();
        kafkaProducer.send(topic, memorialBowedAvroSchema);
    }
}
