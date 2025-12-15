package windeath44.orchestration.domain.port.in;

import windeath44.server.memorial.avro.MemorialCommentedAvroSchema;

public interface MemorialCommentedUseCase {
    void execute(MemorialCommentedAvroSchema memorialCommentedAvroSchema);
}
