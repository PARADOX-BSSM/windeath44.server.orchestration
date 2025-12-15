package windeath44.orchestration.domain.model;

import lombok.experimental.SuperBuilder;
import windeath44.server.memorial.avro.MemorialUpdatedApplyAvroSchema;

@SuperBuilder
public class MemorialUpdatedApplyEvent extends Event<MemorialUpdatedApplyAvroSchema> {
}
