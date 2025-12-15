package windeath44.orchestration.domain.port.in;

import windeath44.server.application.avro.MemorialApplicationAvroSchema;

public interface MemorialApplyUseCase {
    void execute(MemorialApplicationAvroSchema memorialApplicationAvroSchema);
}
