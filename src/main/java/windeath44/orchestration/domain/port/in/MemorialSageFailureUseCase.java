package windeath44.orchestration.domain.port.in;

import com.example.avro.MemorialApplicationAvroSchema;

public interface MemorialSageFailureUseCase {
  void execute(MemorialApplicationAvroSchema memorialAvroSchema);
}
