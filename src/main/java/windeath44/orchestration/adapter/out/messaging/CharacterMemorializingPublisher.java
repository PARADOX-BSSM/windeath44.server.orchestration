package windeath44.orchestration.adapter.out.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import windeath44.orchestration.domain.port.out.CharacterMemorializingEventPublisher;
import windeath44.orchestration.global.kafka.KafkaProducer;

@Component
@RequiredArgsConstructor
public class CharacterMemorializingPublisher implements CharacterMemorializingEventPublisher {
  private final KafkaProducer kafkaProducer;
  @Override
  public void publish() {
    kafkaProducer.send("character-memorializing", null);
  }
}
