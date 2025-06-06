package windeath44.orchestration.adapter.in.messaging;


import com.example.avro.MemorialAvroSchema;
import com.google.common.eventbus.Subscribe;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import windeath44.orchestration.domain.port.in.MemorialCreateUseCase;

@Component
@RequiredArgsConstructor
public class MemorialCreateSubscribe {
  private final MemorialCreateUseCase memorialCreateUseCase;

  @KafkaListener(topics = "memorial-creation", groupId = "memorial")
  public void listen(MemorialAvroSchema message) {
    memorialCreateUseCase.execute(message);
  }
}
