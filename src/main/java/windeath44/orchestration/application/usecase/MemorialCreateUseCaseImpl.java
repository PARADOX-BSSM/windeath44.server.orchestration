package windeath44.orchestration.application.usecase;

import com.example.avro.MemorialApplicationAvroSchema;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import windeath44.orchestration.application.service.MemorialService;
import windeath44.orchestration.domain.mapper.EventMapper;
import windeath44.orchestration.domain.model.MemorialApplicationEvent;
import windeath44.orchestration.domain.model.type.MemorialAction;
import windeath44.orchestration.domain.port.in.MemorialCreateUseCase;
import windeath44.orchestration.domain.repository.EventRepository;

@Component
@RequiredArgsConstructor
public class MemorialCreateUseCaseImpl implements MemorialCreateUseCase {
  private final MemorialService memorialService;
  private final EventMapper eventMapper;
  private final EventRepository eventRepository;

  // 오케스트레이션 서버에서 kafka로 memorial 서버로 생성 요청
  @Override
  @Transactional
  public void execute(MemorialApplicationAvroSchema memorialApplicationAvroSchema) {
    memorialService.execute(MemorialAction.CREATE, memorialApplicationAvroSchema);
    MemorialApplicationEvent memorialEvent = eventMapper.memorialApplicationEvent(memorialApplicationAvroSchema);
    eventRepository.save(memorialEvent);
  }
}