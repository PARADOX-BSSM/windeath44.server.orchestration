package windeath44.orchestration.adapter.in.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import windeath44.orchestration.domain.port.in.MemorialUpdatedUseCase;
import windeath44.server.memorial.avro.MemorialAvroSchema;

@Component
@RequiredArgsConstructor
public class MemorialUpdatedSubscribe {
    private final MemorialUpdatedUseCase memorialUpdatedUseCase;

    @KafkaListener(topics = "memorial-updated", groupId = "memorial")
    public void listen(MemorialAvroSchema memorialAvroSchema) {
        memorialUpdatedUseCase.execute(memorialAvroSchema);
    }
}
