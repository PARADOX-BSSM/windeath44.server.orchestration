package windeath44.orchestration.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import windeath44.orchestration.domain.model.type.Topic;
import windeath44.orchestration.global.kafka.KafkaProducer;
import windeath44.server.memorial.avro.MemorialAvroSchema;

@Service
@RequiredArgsConstructor
public class MemorialVectorizingService {
    private final KafkaProducer kafkaProducer;

    public void execute(MemorialAvroSchema memorialAvroSchema) {
        String topic = Topic.MEMORIAL_VECTORIZING.getTopicName();
        kafkaProducer.send(topic, memorialAvroSchema);
    }
}
