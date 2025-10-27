package windeath44.orchestration.adapter.in.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import windeath44.orchestration.domain.port.in.MemorialBowedUseCase;
import windeath44.server.memorial.avro.MemorialBowedAvroSchema;

@Component
@RequiredArgsConstructor
public class MemorialBowedSubscribe {
    private final MemorialBowedUseCase memorialBowedUseCase;

    @KafkaListener(topics = "memorial-bowed-request", groupId = "memorial")
    public void listen(MemorialBowedAvroSchema message) {
        memorialBowedUseCase.execute(message);
    }
}
