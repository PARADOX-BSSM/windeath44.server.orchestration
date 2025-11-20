package windeath44.orchestration.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import windeath44.orchestration.domain.mapper.EventMapper;
import windeath44.orchestration.domain.model.MemorialApplicationEvent;
import windeath44.orchestration.domain.port.in.MemorialSagaFailureUseCase;
import windeath44.orchestration.domain.repository.EventRepository;
import windeath44.server.application.avro.MemorialApplicationAvroSchema;

@Component
@RequiredArgsConstructor
public class MemorialSagaFailureUseCaseImpl implements MemorialSagaFailureUseCase {
  private final EventMapper eventMapper;
  private final EventRepository eventRepository;
  @Override
  public void execute(MemorialApplicationAvroSchema memorialAvroSchema) {
    MemorialApplicationEvent memorialApplicationEvent = eventMapper.memorialApplicationCompensateEvent(memorialAvroSchema);
    eventRepository.save(memorialApplicationEvent);
  }
}
