package windeath44.orchestration.domain.model;

import lombok.experimental.SuperBuilder;
import windeath44.server.memorial.avro.MemorialCommentedAvroSchema;

@SuperBuilder
public class MemorialCommentedEvent extends Event<MemorialCommentedAvroSchema> {
}
