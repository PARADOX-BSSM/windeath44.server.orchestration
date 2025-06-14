package windeath44.orchestration.application.usecase;

import com.example.avro.MemorialAvroSchema;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import windeath44.orchestration.application.service.MemorialApplicationService;
import windeath44.orchestration.domain.mapper.EventMapper;
import windeath44.orchestration.domain.model.MemorialEvent;
import windeath44.orchestration.domain.model.type.MemorialApplicationAction;
import windeath44.orchestration.domain.port.in.MemorialApplicationCancelUseCase;
import windeath44.orchestration.domain.repository.EventRepository;

@Component
@RequiredArgsConstructor
public class MemorialApplicationCancelUseCaseImpl implements MemorialApplicationCancelUseCase {
  private final EventMapper eventMapper;
  private final EventRepository eventRepository;
  private final MemorialApplicationService memorialApplicationService;

  @Override
  public void execute(MemorialAvroSchema memorialAvroSchema) {
    memorialApplicationService.execute(MemorialApplicationAction.CANCEL, memorialAvroSchema);
    MemorialEvent memorialEvent = eventMapper.memorialCompensateEvent(memorialAvroSchema);
    eventRepository.save(memorialEvent);
  }
}
