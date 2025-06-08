package windeath44.orchestration.adapter.in.messaging;

import com.example.avro.MemorialAvroSchema;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import windeath44.orchestration.domain.port.in.CharacterMemorializingUseCase;

@Component
@RequiredArgsConstructor
public class CharacterMemorializingSubscribe {
  private final CharacterMemorializingUseCase characterMemorializingUseCase;

  @KafkaListener(topics = "memorial-created", groupId = "memorial")
  public void listen(MemorialAvroSchema message) {
    characterMemorializingUseCase.execute(message);
  }

}
