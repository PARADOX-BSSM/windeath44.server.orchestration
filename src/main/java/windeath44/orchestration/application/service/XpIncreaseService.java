package windeath44.orchestration.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import windeath44.orchestration.domain.model.type.Topic;
import windeath44.orchestration.domain.mapper.XpMapper;
import windeath44.orchestration.global.kafka.KafkaProducer;
import windeath44.server.user.avro.XpIncreaseRequest;

@Service
@RequiredArgsConstructor
public class XpIncreaseService {
    private static final String BOW_SOURCE = "memorial_bow";
    private static final long BOW_XP_AMOUNT = 20L;

    private final KafkaProducer kafkaProducer;
    private final XpMapper xpMapper;

    public void publishBowXp(String userId) {
        XpIncreaseRequest request = xpMapper.toXpIncreaseRequest(userId, BOW_XP_AMOUNT, BOW_SOURCE);

        kafkaProducer.send(Topic.XP_INCREASE.getTopicName(), request);
    }
}
