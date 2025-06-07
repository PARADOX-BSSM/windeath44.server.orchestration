package windeath44.orchestration.adapter.out.messaging;

import com.example.avro.MemorialAvroSchema;
import org.springframework.stereotype.Component;
import windeath44.orchestration.domain.port.out.CharacterEventPublisher;
import windeath44.orchestration.global.kafka.KafkaProducer;

@Component
public class CharacterMemorializingPublisher implements CharacterEventPublisher {
  private final KafkaProducer kafkaProducer;

  public CharacterMemorializingPublisher(KafkaProducer kafkaProducer) {
    this.kafkaProducer = kafkaProducer;
  }

  @Override
  public void publish(MemorialAvroSchema memorialAvroSchema) {
    kafkaProducer.send("character-memorializing", memorialAvroSchema);
  }
}
