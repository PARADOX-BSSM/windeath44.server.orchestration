package windeath44.orchestration.domain.port.in;

import com.example.avro.CharacterAvroSchema;

public interface CharacterMemorializedUseCase {
  void execute(CharacterAvroSchema characterAvroSchema);
}
