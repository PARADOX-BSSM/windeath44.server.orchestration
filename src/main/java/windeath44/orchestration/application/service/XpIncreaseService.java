package windeath44.orchestration.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import windeath44.orchestration.domain.model.type.Topic;
import windeath44.orchestration.domain.mapper.XpMapper;
import windeath44.orchestration.domain.model.type.XpRewardType;
import windeath44.orchestration.global.kafka.KafkaProducer;
import windeath44.server.user.avro.XpIncreaseRequest;

@Service
@RequiredArgsConstructor
public class XpIncreaseService {
    private final KafkaProducer kafkaProducer;
    private final XpMapper xpMapper;

    public void publishBowXp(String userId) {
        publish(userId, XpRewardType.MEMORIAL_BOW);
    }

    public void publishCommentXp(String userId) {
        publish(userId, XpRewardType.MEMORIAL_COMMENT);
    }

    private void publish(String userId, XpRewardType rewardType) {
        XpIncreaseRequest request = xpMapper.toXpIncreaseRequest(
                userId,
                rewardType.getAmount(),
                rewardType.getSource(),
                rewardType.buildRequestId()
        );

        kafkaProducer.send(Topic.XP_INCREASE.getTopicName(), request);
    }
}
