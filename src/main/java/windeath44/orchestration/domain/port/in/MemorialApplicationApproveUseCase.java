package windeath44.orchestration.domain.port.in;

import com.example.avro.CharacterAvroSchema;

public interface MemorialApplicationApproveUseCase {
  void execute(CharacterAvroSchema characterAvroSchema);
}
