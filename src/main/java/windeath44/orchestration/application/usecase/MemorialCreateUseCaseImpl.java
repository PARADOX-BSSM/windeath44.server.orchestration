package windeath44.orchestration.application.usecase;

import com.example.avro.MemorialAvroSchema;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import windeath44.orchestration.application.service.AnimeService;
import windeath44.orchestration.application.service.MemorialService;
import windeath44.orchestration.domain.port.in.MemorialCreateUseCase;

@Component
@RequiredArgsConstructor
public class MemorialCreateUseCaseImpl implements MemorialCreateUseCase {
  private final MemorialService memorialService;
  private final AnimeService animeService;

  // 오케스트레이션 서버에서 kafka로 memorial 서버로 생성 요청
  // 오케스트레이션 서버에서 kafka로 anime 서버의 캐릭터 상태를 'NOT_MEMORIALIZING' -> 'MEMORIALIZING'으로 변환
  @Override
  public void execute(MemorialAvroSchema memorialAvroSchema) {
    memorialService.create(memorialAvroSchema);
    animeService.memorial();
  }
}
