package windeath44.orchestration.domain.port.in;

import com.example.avro.MemorialAvroSchema;

public interface MemorialCreateUseCase {
  void execute(MemorialAvroSchema memorialAvroSchema);
  void compensate(MemorialAvroSchema memorialAvroSchema);
}
