package windeath44.orchestration.domain.port.in;

import com.example.avro.MemorialApplicationAvroSchema;

public interface MemorialSageSuccessUseCase {
  void execute(MemorialApplicationAvroSchema memorialAvroSchema);
}
