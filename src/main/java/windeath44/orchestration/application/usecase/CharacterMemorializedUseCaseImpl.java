package windeath44.orchestration.application.usecase;

import com.example.avro.CharacterAvroSchema;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import windeath44.orchestration.application.service.MemorialApplicationService;
import windeath44.orchestration.domain.mapper.EventMapper;
import windeath44.orchestration.domain.model.CharacterEvent;
import windeath44.orchestration.domain.model.type.MemorialApplicationAction;
import windeath44.orchestration.domain.port.in.CharacterMemorializedUseCase;
import windeath44.orchestration.domain.repository.EventRepository;

@Component
@RequiredArgsConstructor
public class CharacterMemorializedUseCaseImpl implements CharacterMemorializedUseCase {
  private final EventMapper eventMapper;
  private final EventRepository eventRepository;
  private final MemorialApplicationService memorialApplicationService;

  @Override
  public void execute(CharacterAvroSchema characterAvroSchema) {
    memorialApplicationService.execute(MemorialApplicationAction.APPROVE, characterAvroSchema);git 
    CharacterEvent characterEvent = eventMapper.characterEvent(characterAvroSchema);
    eventRepository.save(characterEvent);
  }
}
