package windeath44.orchestration.domain.port.in;

import com.example.avro.MemorialAvroSchema;

public interface MemorialApplicationCancelUseCase {
  void execute(MemorialAvroSchema memorialAvroSchema);
}
