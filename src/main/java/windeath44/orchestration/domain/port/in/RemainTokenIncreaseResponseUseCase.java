package windeath44.orchestration.domain.port.in;


import windeath44.server.user.avro.RemainTokenIncreaseResponse;

public interface RemainTokenIncreaseResponseUseCase {
    void execute(RemainTokenIncreaseResponse remainTokenIncreaseResponse);
}
