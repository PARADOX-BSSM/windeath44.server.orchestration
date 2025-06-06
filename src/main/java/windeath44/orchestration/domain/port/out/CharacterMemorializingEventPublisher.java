package windeath44.orchestration.domain.port.out;

import com.example.avro.MemorialAvroSchema;

public interface CharacterMemorializingEventPublisher {
  void publish(MemorialAvroSchema memorialAvroSchema);
}
