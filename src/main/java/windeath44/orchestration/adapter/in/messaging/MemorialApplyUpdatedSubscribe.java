package windeath44.orchestration.adapter.in.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import windeath44.orchestration.domain.port.in.MemorialApplyUpdatedUseCase;
import windeath44.server.memorial.avro.MemorialUpdatedApplyAvroSchema;

@Component
@RequiredArgsConstructor
public class MemorialApplyUpdatedSubscribe {
    private final MemorialApplyUpdatedUseCase memorialApplyUpdatedUseCase;

    @KafkaListener(topics = "memorial-updated-apply-request", groupId = "memorial")
    public void listen(MemorialUpdatedApplyAvroSchema message) {
        memorialApplyUpdatedUseCase.execute(message);
    }
}
