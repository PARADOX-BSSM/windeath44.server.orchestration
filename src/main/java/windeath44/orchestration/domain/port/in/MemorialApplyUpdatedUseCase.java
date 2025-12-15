package windeath44.orchestration.domain.port.in;

import windeath44.server.memorial.avro.MemorialUpdatedApplyAvroSchema;

public interface MemorialApplyUpdatedUseCase {
    void execute(MemorialUpdatedApplyAvroSchema memorialUpdatedApplyAvroSchema);
}
