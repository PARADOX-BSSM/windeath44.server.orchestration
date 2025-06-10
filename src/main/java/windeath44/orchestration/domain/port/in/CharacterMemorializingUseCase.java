package windeath44.orchestration.domain.port.in;

import com.example.avro.MemorialApplicationAvroSchema;

public interface CharacterMemorializingUseCase {
  void execute(MemorialApplicationAvroSchema memorialApplicationAvroSchema);
  void compensate(MemorialApplicationAvroSchema memorialApplicationAvroSchema);
}
