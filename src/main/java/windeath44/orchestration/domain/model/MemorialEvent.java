package windeath44.orchestration.domain.model;

import lombok.experimental.SuperBuilder;
import windeath44.orchestration.domain.model.type.EventType;
import windeath44.server.memorial.avro.MemorialAvroSchema;

@SuperBuilder
public class MemorialEvent extends Event<MemorialAvroSchema>{

}