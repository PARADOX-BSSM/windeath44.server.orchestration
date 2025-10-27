package windeath44.orchestration.adapter.in.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import windeath44.orchestration.domain.port.in.MemorialSagaFailureUseCase;
import windeath44.server.application.avro.MemorialApplicationAvroSchema;

@Component
@RequiredArgsConstructor
public class MemorialSagaFailureSubscribe {
  private final MemorialSagaFailureUseCase memorialSageFailureUseCase;

  @KafkaListener(topics="memorial-application-cancel-response", groupId = "memorial")
  public void listen(MemorialApplicationAvroSchema message) {
    memorialSageFailureUseCase.execute(message);
  }
}
