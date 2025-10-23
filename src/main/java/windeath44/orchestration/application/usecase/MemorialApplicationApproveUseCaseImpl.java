package windeath44.orchestration.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import windeath44.orchestration.application.service.MemorialApplicationService;
import windeath44.orchestration.domain.mapper.EventMapper;
import windeath44.orchestration.domain.model.MemorialEvent;
import windeath44.orchestration.domain.model.type.MemorialApplicationAction;
import windeath44.orchestration.domain.port.in.MemorialApplicationApproveUseCase;
import windeath44.orchestration.domain.repository.EventRepository;
import windeath44.server.memorial.avro.MemorialAvroSchema;

@Component
@RequiredArgsConstructor
public class MemorialApplicationApproveUseCaseImpl implements MemorialApplicationApproveUseCase {
  private final EventMapper eventMapper;
  private final EventRepository eventRepository;
  private final MemorialApplicationService memorialApplicationService;

  @Override
  public void execute(MemorialAvroSchema memorialAvroSchema) {
    memorialApplicationService.execute(MemorialApplicationAction.APPROVE, memorialAvroSchema);
    MemorialEvent memorialEvent = eventMapper.memorialEvent(memorialAvroSchema);
    eventRepository.save(memorialEvent);
  }
}
