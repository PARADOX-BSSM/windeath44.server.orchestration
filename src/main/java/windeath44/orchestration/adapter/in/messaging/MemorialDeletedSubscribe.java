package windeath44.orchestration.adapter.in.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import windeath44.orchestration.domain.port.in.MemorialDeletedUseCase;
import windeath44.server.memorial.avro.MemorialAvroSchema;

@Component
@RequiredArgsConstructor
public class MemorialDeletedSubscribe {
    private final MemorialDeletedUseCase memorialDeletedUseCase;

    @KafkaListener(topics = "memorial-deleted", groupId = "memorial")
    public void listen(MemorialAvroSchema memorialAvroSchema) {
        memorialDeletedUseCase.execute(memorialAvroSchema);
    }
}
