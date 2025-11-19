package windeath44.orchestration.domain.port.in;

import windeath44.server.memorial.avro.MemorialAvroSchema;

public interface MemorialDeletedUseCase {
    void execute(MemorialAvroSchema memorialAvroSchema);
}
