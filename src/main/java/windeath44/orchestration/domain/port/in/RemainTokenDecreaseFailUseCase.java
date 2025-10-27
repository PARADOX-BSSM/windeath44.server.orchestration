package windeath44.orchestration.domain.port.in;


import windeath44.server.user.avro.RemainTokenDecreaseResponse;

public interface RemainTokenDecreaseFailUseCase {
    void execute(RemainTokenDecreaseResponse remainTokenDecreaseResponse);
}
