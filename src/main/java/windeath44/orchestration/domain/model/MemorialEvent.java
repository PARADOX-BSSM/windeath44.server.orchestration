package windeath44.orchestration.domain.model;

import com.example.avro.MemorialAvroSchema;
import lombok.experimental.SuperBuilder;
import windeath44.orchestration.domain.model.type.EventType;

@SuperBuilder
public class MemorialEvent extends Event<MemorialAvroSchema>{

}