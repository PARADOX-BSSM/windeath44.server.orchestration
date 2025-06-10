package windeath44.orchestration.application.usecase;

import com.example.avro.MemorialAvroSchema;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import windeath44.orchestration.application.service.MemorialService;
import windeath44.orchestration.domain.EventMapper;
import windeath44.orchestration.domain.model.MemorialEvent;
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
  public void execute(MemorialAvroSchema memorialAvroSchema) {
    memorialService.execute(MemorialAction.CREATE, memorialAvroSchema);
    MemorialEvent memorialEvent = eventMapper.memorialEvent(memorialAvroSchema);
    eventRepository.save(memorialEvent);
  }

  // 보상 트랜잭션 ( 기존에 create 했던 과정을 돌이켜야함 )
  @Override
  public void compensate(MemorialAvroSchema memorialAvroSchema) {
    memorialService.execute(MemorialAction.COMPENSATE, memorialAvroSchema);
    MemorialEvent memorialEvent = eventMapper.memorialCompensate(memorialAvroSchema);
    eventRepository.save(memorialEvent);
  }
}
