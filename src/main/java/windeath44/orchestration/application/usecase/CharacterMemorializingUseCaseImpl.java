package windeath44.orchestration.application.usecase;

import com.example.avro.MemorialAvroSchema;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import windeath44.orchestration.application.service.AnimeService;
import windeath44.orchestration.domain.EventMapper;
import windeath44.orchestration.domain.model.Event;
import windeath44.orchestration.domain.model.MemorialEvent;
import windeath44.orchestration.domain.port.in.CharacterMemorializingUseCase;
import windeath44.orchestration.domain.repository.EventRepository;

@Component
@RequiredArgsConstructor
public class CharacterMemorializingUseCaseImpl implements CharacterMemorializingUseCase {
  private final EventRepository eventRepository;
  private final AnimeService animeService;
  private final EventMapper eventMapper;

  // 오케스트레이션 서버에서 kafka로 anime 서버의 캐릭터 상태를 'NOT_MEMORIALIZING' -> 'MEMORIALIZING'으로 변환
  @Override
  @Transactional
  public void execute(MemorialAvroSchema memorialAvroSchema) {
    animeService.memorial();
    MemorialEvent event = eventMapper.memorialEvent(memorialAvroSchema);
    eventRepository.save(event);
  }

  @Override
  public void compensate(MemorialAvroSchema memorialAvroSchema) {

  }
}
