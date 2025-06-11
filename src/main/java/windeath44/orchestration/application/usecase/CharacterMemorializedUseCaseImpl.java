package windeath44.orchestration.application.usecase;

import com.example.avro.CharacterAvroSchema;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import windeath44.orchestration.application.service.AnimeService;
import windeath44.orchestration.domain.EventMapper;
import windeath44.orchestration.domain.model.CharacterEvent;
import windeath44.orchestration.domain.port.in.CharacterMemorializedUseCase;
import windeath44.orchestration.domain.repository.EventRepository;

@Component
@RequiredArgsConstructor
public class CharacterMemorializedUseCaseImpl implements CharacterMemorializedUseCase {
  private final EventMapper eventMapper;
  private final EventRepository eventRepository;

  @Override
  public void execute(CharacterAvroSchema characterAvroSchema) {
    CharacterEvent characterEvent = eventMapper.characterEvent(characterAvroSchema);
  }
}
