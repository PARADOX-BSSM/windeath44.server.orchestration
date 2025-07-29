package windeath44.orchestration.application.service;

import com.example.avro.MemorialAvroSchema;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import windeath44.orchestration.domain.port.out.CharacterEventPublisher;

@Service
@RequiredArgsConstructor
public class AnimeService {
  private final CharacterEventPublisher characterMemorializingEventPublisher;

  public void memorial(MemorialAvroSchema memorialAvroSchema) {
    characterMemorializingEventPublisher.publish(memorialAvroSchema);
  }
}
