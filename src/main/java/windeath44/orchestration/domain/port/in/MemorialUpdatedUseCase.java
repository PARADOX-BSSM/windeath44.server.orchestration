package windeath44.orchestration.domain.port.in;

import windeath44.server.memorial.avro.MemorialAvroSchema;

public interface MemorialUpdatedUseCase {
    void execute(MemorialAvroSchema memorialAvroSchema);
}
