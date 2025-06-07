package windeath44.orchestration.application.usecase;

import com.example.avro.MemorialAvroSchema;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import windeath44.orchestration.application.service.AnimeService;
import windeath44.orchestration.domain.port.in.CharacterMemorializingUseCase;

@Component
@RequiredArgsConstructor
public class CharacterMemorializingUseCaseImpl implements CharacterMemorializingUseCase {
  private final AnimeService animeService;

  // 오케스트레이션 서버에서 kafka로 anime 서버의 캐릭터 상태를 'NOT_MEMORIALIZING' -> 'MEMORIALIZING'으로 변환
  @Override
  public void execute(MemorialAvroSchema memorialAvroSchema) {
    animeService.memorial();
  }
}
