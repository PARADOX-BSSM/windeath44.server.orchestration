package windeath44.orchestration.domain.port.out;

import com.example.avro.MemorialAvroSchema;
import windeath44.orchestration.domain.model.MemorialAction;

public interface MemorialEventPublisher {
  void publish(MemorialAvroSchema memorialAvroSchema);
  MemorialAction getAction();
}
