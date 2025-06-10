package windeath44.orchestration.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import windeath44.orchestration.domain.port.out.CharacterEventPublisher;

@Service
@RequiredArgsConstructor
public class AnimeService {
  private final CharacterEventPublisher characterMemorializingEventPublisher;

  public void memorial() {
    characterMemorializingEventPublisher.publish(null);
  }
}
