package windeath44.orchestration.domain.mapper;

import org.springframework.stereotype.Component;
import windeath44.orchestration.domain.model.XpIncreaseEvent;
import windeath44.orchestration.domain.model.type.EventType;
import windeath44.orchestration.domain.model.type.XpRewardType;
import windeath44.server.user.avro.XpIncreaseRequest;
import windeath44.server.user.avro.XpIncreaseResponse;

@Component
public class XpMapper {

    public XpIncreaseRequest toXpIncreaseRequest(String userId, long amount, String source, String requestId) {
        return XpIncreaseRequest.newBuilder()
                .setUserId(userId)
                .setIncreaseAmount(amount)
                .setSource(source)
                .setRequestId(requestId)
                .build();
    }

    public XpIncreaseEvent xpIncreaseResponse(XpIncreaseResponse xpIncreaseResponse) {
        String aggregateId = XpRewardType.fromRequestId(xpIncreaseResponse.getRequestId())
                .map(type -> type.aggregateId(xpIncreaseResponse.getUserId()))
                .orElse("xp-increase-" + xpIncreaseResponse.getUserId());
        String aggregateType = "XP_INCREASE";

        return XpIncreaseEvent.builder()
                .aggregateId(aggregateId)
                .aggregateType(aggregateType)
                .eventType(EventType.XP_INCREASE_RESPONSE)
                .eventData(xpIncreaseResponse)
                .build();
    }
}
