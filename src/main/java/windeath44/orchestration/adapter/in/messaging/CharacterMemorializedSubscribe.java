package windeath44.orchestration.adapter.in.messaging;

import com.example.avro.CharacterAvroSchema;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import windeath44.orchestration.domain.port.in.CharacterMemorializedUseCase;

@Component
@RequiredArgsConstructor
public class CharacterMemorializedSubscribe {
    private final CharacterMemorializedUseCase characterMemorializedUseCase;

    @KafkaListener(topics="character-memorialized-response", groupId = "memorial")
    public void listen(CharacterAvroSchema message) {
      characterMemorializedUseCase.execute(message);
    }
}
