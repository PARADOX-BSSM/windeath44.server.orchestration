package windeath44.orchestration.application.usecase;

import com.example.avro.CharacterAvroSchema;
import com.example.avro.MemorialAvroSchema;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import windeath44.orchestration.application.service.MemorialService;
import windeath44.orchestration.domain.mapper.EventMapper;
import windeath44.orchestration.domain.model.CharacterEvent;
import windeath44.orchestration.domain.model.MemorialEvent;
import windeath44.orchestration.domain.model.type.MemorialAction;
import windeath44.orchestration.domain.port.in.MemorialDeleteUseCase;
import windeath44.orchestration.domain.repository.EventRepository;

@Component
@RequiredArgsConstructor
public class MemorialDeleteUseCaseImpl implements MemorialDeleteUseCase {
  private final MemorialService memorialService;
  private final EventMapper eventMapper;
  private final EventRepository eventRepository;

  // 보상 트랜잭션 ( 기존에 create 했던 과정을 돌이켜야함 )
  @Override
  public void execute(CharacterAvroSchema characterAvroSchema) {
    memorialService.execute(MemorialAction.COMPENSATE, characterAvroSchema);
    CharacterEvent characterEvent = eventMapper.characterCompensateEvent(characterAvroSchema);
    eventRepository.save(characterEvent);
  }
}
