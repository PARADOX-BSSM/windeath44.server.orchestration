package windeath44.orchestration.adapter.in.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import windeath44.orchestration.domain.port.in.MemorialCommentedUseCase;
import windeath44.server.memorial.avro.MemorialCommentedAvroSchema;

@Component
@RequiredArgsConstructor
public class MemorialCommentedSubscribe {
    private final MemorialCommentedUseCase memorialCommentedUseCase;

    @KafkaListener(topics = "memorial-commented-request", groupId = "memorial")
    public void listen(MemorialCommentedAvroSchema message) {
        memorialCommentedUseCase.execute(message);
    }
}
