package windeath44.orchestration.domain.port.out;

import com.example.avro.MemorialAvroSchema;

public interface CharacterEventPublisher {
  void publish(MemorialAvroSchema memorialAvroSchema);
}
