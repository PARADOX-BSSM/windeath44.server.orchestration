package windeath44.orchestration.adapter.in.messaging;

import com.example.avro.MemorialAvroSchema;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import windeath44.orchestration.domain.port.in.MemorialApplicationCancelUseCase;

@Component
@RequiredArgsConstructor
public class MemorialApplicationCancelSubscribe {
  private final MemorialApplicationCancelUseCase memorialApplicationCancelUseCase;

  @KafkaListener(topics={"memorial-creation-fail-response"}, groupId = "memorial")
  public void listen(MemorialAvroSchema message) {
    memorialApplicationCancelUseCase.execute(message);
  }
}