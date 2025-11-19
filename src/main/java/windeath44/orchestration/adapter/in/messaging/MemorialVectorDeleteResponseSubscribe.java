package windeath44.orchestration.adapter.in.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import windeath44.orchestration.domain.port.in.MemorialVectorDeleteResponseUseCase;
import windeath44.server.memorial.avro.MemorialAvroSchema;

@Component
@RequiredArgsConstructor
public class MemorialVectorDeleteResponseSubscribe {
    private final MemorialVectorDeleteResponseUseCase memorialVectorDeleteResponseUseCase;

    @KafkaListener(topics = "memorial-vector-delete-response", groupId = "memorial")
    public void listen(MemorialAvroSchema memorialAvroSchema) {
        memorialVectorDeleteResponseUseCase.execute(memorialAvroSchema);
    }
}
