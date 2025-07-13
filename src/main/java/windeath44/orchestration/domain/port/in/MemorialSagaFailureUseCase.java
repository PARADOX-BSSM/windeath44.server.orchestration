package windeath44.orchestration.domain.port.in;

import com.example.avro.MemorialApplicationAvroSchema;

public interface MemorialSagaFailureUseCase {
  void execute(MemorialApplicationAvroSchema memorialAvroSchema);
}
