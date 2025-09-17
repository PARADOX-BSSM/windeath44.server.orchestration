package windeath44.orchestration.domain.port.in;

import com.example.avro.MemorialAvroSchema;

public interface MemorialApplicationApproveUseCase {
  void execute(MemorialAvroSchema memorialAvroSchema);
}
