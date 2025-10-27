package windeath44.orchestration.domain.port.in;


import windeath44.server.application.avro.MemorialApplicationAvroSchema;

public interface MemorialSagaSuccessUseCase {
  void execute(MemorialApplicationAvroSchema memorialAvroSchema);
}
