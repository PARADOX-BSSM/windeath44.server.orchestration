package windeath44.orchestration.adapter.in.messaging;

import com.example.avro.MemorialApplicationAvroSchema;
import com.example.avro.MemorialAvroSchema;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import windeath44.orchestration.domain.port.in.MemorialCreateUseCase;

@Component
@RequiredArgsConstructor
public class MemorialCreateSubscribe {
  private final MemorialCreateUseCase memorialCreateUseCase;

  @KafkaListener(topics = "memorial-application-approved-request", groupId = "memorial")
  public void listen(MemorialApplicationAvroSchema message) {
    memorialCreateUseCase.execute(message);
  }

}
