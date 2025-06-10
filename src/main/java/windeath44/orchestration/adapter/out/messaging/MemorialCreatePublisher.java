package windeath44.orchestration.adapter.out.messaging;

import lombok.RequiredArgsConstructor;
import org.apache.avro.specific.SpecificRecord;
import org.springframework.stereotype.Component;
import windeath44.orchestration.domain.model.type.MemorialAction;
import windeath44.orchestration.domain.model.type.Topic;
import windeath44.orchestration.domain.port.out.MemorialEventPublisher;
import windeath44.orchestration.global.kafka.KafkaProducer;

@Component
@RequiredArgsConstructor
public class MemorialCreatePublisher implements MemorialEventPublisher {
  private final KafkaProducer kafkaProducer;

  @Override
  public <T extends SpecificRecord> void publish(T memorialAvroSchema) {
    String topic = Topic.MEMORIAL_CREATION.getTopicName();
    kafkaProducer.send(topic, memorialAvroSchema);
  }

  @Override
  public MemorialAction getAction() {
    return MemorialAction.CREATE;
  }
}
