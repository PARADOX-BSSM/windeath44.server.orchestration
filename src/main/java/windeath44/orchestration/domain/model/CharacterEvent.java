package windeath44.orchestration.domain.model;

import com.example.avro.CharacterAvroSchema;
import com.example.avro.MemorialAvroSchema;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class CharacterEvent extends Event<CharacterAvroSchema>{

}