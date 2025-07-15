package windeath44.orchestration.adapter.in.messaging;

import com.example.avro.MemorialApplicationAvroSchema;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import windeath44.orchestration.domain.port.in.MemorialSagaSuccessUseCase;

@Component
@RequiredArgsConstructor
public class MemorialSagaSuccessUseCaseSubscribe {
  private final MemorialSagaSuccessUseCase memorialSageSuccessUseCase;

  @KafkaListener(topics = "memorial-creation-orchestration-complete", groupId = "memorial")
  public void listen(MemorialApplicationAvroSchema message) {
    memorialSageSuccessUseCase.execute(message);
  }
}
