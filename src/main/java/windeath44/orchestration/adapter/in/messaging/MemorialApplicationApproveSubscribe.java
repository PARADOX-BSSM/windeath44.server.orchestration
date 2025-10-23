package windeath44.orchestration.adapter.in.messaging;

import com.example.avro.MemorialAvroSchema;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import windeath44.orchestration.domain.port.in.MemorialApplicationApproveUseCase;

@Component
@RequiredArgsConstructor
public class MemorialApplicationApproveSubscribe {
    private final MemorialApplicationApproveUseCase memorialApplicationApproveUseCase;

    @KafkaListener(topics="memorial-creation-response", groupId = "memorial")
    public void listen(MemorialAvroSchema message) {
      memorialApplicationApproveUseCase.execute(message);
    }
}
