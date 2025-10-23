package windeath44.orchestration.domain.model;

import lombok.experimental.SuperBuilder;
import windeath44.server.application.avro.MemorialApplicationAvroSchema;

@SuperBuilder
public class MemorialApplicationEvent extends Event<MemorialApplicationAvroSchema> {
}
