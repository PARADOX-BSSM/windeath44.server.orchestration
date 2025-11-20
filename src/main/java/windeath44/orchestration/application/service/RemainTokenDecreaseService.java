package windeath44.orchestration.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import windeath44.orchestration.domain.model.type.Topic;
import windeath44.orchestration.global.kafka.KafkaProducer;
import windeath44.server.chatbot.avro.ChatAvroSchema;

@Service
@RequiredArgsConstructor
public class RemainTokenDecreaseService {
    private final KafkaProducer kafkaProducer;

    public void execute(ChatAvroSchema chatAvroSchema) {
        String topic = Topic.REMAIN_TOKEN_DECREASE.getTopicName();
        kafkaProducer.send(topic, chatAvroSchema);
    }
}
