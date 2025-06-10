package windeath44.orchestration.domain.port.in;

import com.example.avro.MemorialAvroSchema;

public interface MemorialDeleteUseCase {
  void execute(MemorialAvroSchema memorialAvroSchema);
}
