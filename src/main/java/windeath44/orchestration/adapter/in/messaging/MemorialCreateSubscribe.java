package windeath44.orchestration.adapter.in.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import windeath44.orchestration.domain.port.in.MemorialCreateUseCase;
import windeath44.server.application.avro.MemorialApplicationAvroSchema;

@Component
@RequiredArgsConstructor
public class MemorialCreateSubscribe {
  private final MemorialCreateUseCase memorialCreateUseCase;

  @KafkaListener(topics = "memorial-application-approved-request", groupId = "memorial")
  public void listen(MemorialApplicationAvroSchema message) {
    memorialCreateUseCase.execute(message);
  }

}
