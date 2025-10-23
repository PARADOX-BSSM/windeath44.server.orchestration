package windeath44.orchestration.domain.port.in;


import windeath44.server.user.avro.RemainTokenDecreaseResponse;

public interface RemainTokenDecreaseSuccessUseCase {
    void execute(RemainTokenDecreaseResponse remainTokenDecreaseResponse);
}
