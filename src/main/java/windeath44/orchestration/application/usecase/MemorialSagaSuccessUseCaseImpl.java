package windeath44.orchestration.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import windeath44.orchestration.domain.mapper.EventMapper;
import windeath44.orchestration.domain.model.MemorialApplicationEvent;
import windeath44.orchestration.domain.port.in.MemorialSagaSuccessUseCase;
import windeath44.orchestration.domain.repository.EventRepository;
import windeath44.server.application.avro.MemorialApplicationAvroSchema;

@Component
@RequiredArgsConstructor
public class MemorialSagaSuccessUseCaseImpl implements MemorialSagaSuccessUseCase {
  private final EventRepository eventRepository;
  private final EventMapper eventMapper;

  @Override
  public void execute(MemorialApplicationAvroSchema memorialAvroSchema) {
    MemorialApplicationEvent memorialApplicationEvent = eventMapper.memorialApplicationEvent(memorialAvroSchema);
    eventRepository.save(memorialApplicationEvent);
  }
}
