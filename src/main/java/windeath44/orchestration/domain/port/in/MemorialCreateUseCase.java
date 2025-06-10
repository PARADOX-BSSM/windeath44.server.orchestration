package windeath44.orchestration.domain.port.in;

import com.example.avro.MemorialApplicationAvroSchema;
import com.example.avro.MemorialAvroSchema;

public interface MemorialCreateUseCase {
  void execute(MemorialApplicationAvroSchema memorialApplicationAvroSchema);
  void compensate(MemorialAvroSchema memorialAvroSchema);
}
