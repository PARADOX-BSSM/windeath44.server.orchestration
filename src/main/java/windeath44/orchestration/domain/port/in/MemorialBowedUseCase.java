package windeath44.orchestration.domain.port.in;

import windeath44.server.memorial.avro.MemorialBowedAvroSchema;

public interface MemorialBowedUseCase {
    void execute(MemorialBowedAvroSchema memorialBowedAvroSchema);
}
