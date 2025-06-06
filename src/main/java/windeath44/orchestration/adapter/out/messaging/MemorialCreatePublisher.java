package windeath44.orchestration.adapter.out.messaging;
import com.example.avro.MemorialAvroSchema;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import windeath44.orchestration.domain.port.out.MemorialCreateEventPublisher;
import windeath44.orchestration.global.kafka.KafkaProducer;

@Component
@RequiredArgsConstructor
public class MemorialCreatePublisher implements MemorialCreateEventPublisher {
  private final KafkaProducer kafkaProducer;

  @Override
  public void publish(MemorialAvroSchema memorialAvroSchema) {
    kafkaProducer.send("memorial-creation", memorialAvroSchema);
  }
}
