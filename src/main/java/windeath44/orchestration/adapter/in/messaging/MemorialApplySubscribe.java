package windeath44.orchestration.adapter.in.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import windeath44.orchestration.domain.port.in.MemorialApplyUseCase;
import windeath44.server.application.avro.MemorialApplicationAvroSchema;

@Component
@RequiredArgsConstructor
public class MemorialApplySubscribe {
    private final MemorialApplyUseCase memorialApplyUseCase;

    @KafkaListener(topics = "memorial-apply-request", groupId = "memorial")
    public void listen(MemorialApplicationAvroSchema message) {
        memorialApplyUseCase.execute(message);
    }
}
