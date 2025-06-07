package windeath44.orchestration.adapter.out.messaging;

import com.example.avro.MemorialAvroSchema;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import windeath44.orchestration.domain.model.MemorialAction;
import windeath44.orchestration.domain.port.out.MemorialEventPublisher;
import windeath44.orchestration.global.kafka.KafkaProducer;

@Component
@RequiredArgsConstructor
public class MemorialDeletePublisher implements MemorialEventPublisher {
  private final KafkaProducer kafkaProducer;
  @Override
  public void publish(MemorialAvroSchema memorialAvroSchema) {
      kafkaProducer.send("memorial-deletion", memorialAvroSchema);
  }

  @Override
  public MemorialAction getAction() {
    return MemorialAction.COMPENSATE;
  }
}
