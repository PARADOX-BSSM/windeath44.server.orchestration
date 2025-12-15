package windeath44.orchestration.domain.port.in;

import windeath44.server.application.avro.MemorialAppliedAvroSchema;

public interface MemorialApplyUseCase {
    void execute(MemorialAppliedAvroSchema memorialAppliedAvroSchema);
}
