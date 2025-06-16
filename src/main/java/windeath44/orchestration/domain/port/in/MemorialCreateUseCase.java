package windeath44.orchestration.domain.port.in;

import com.example.avro.MemorialApplicationAvroSchema;

public interface MemorialCreateUseCase {
  void execute(MemorialApplicationAvroSchema memorialApplicationAvroSchema);
}
