package windeath44.orchestration.domain.port.in;


import windeath44.server.application.avro.MemorialApplicationAvroSchema;

public interface MemorialSagaFailureUseCase {
  void execute(MemorialApplicationAvroSchema memorialAvroSchema);
}
