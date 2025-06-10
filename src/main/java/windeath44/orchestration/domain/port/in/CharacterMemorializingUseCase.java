package windeath44.orchestration.domain.port.in;

import com.example.avro.MemorialApplicationAvroSchema;
import com.example.avro.MemorialAvroSchema;

public interface CharacterMemorializingUseCase {
  void execute(MemorialAvroSchema memorialAvroSchema);
  void compensate(MemorialApplicationAvroSchema memorialApplicationAvroSchema);
}
