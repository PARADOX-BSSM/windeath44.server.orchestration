package windeath44.orchestration.domain.port.in;

import windeath44.server.user.avro.XpIncreaseResponse;

public interface XpIncreaseResponseUseCase {
    void execute(XpIncreaseResponse xpIncreaseResponse);
}
