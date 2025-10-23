package windeath44.orchestration.domain.port.in;

import com.example.user.avro.RemainTokenIncreaseResponse;

public interface RemainTokenIncreaseResponseUseCase {
    void execute(RemainTokenIncreaseResponse remainTokenIncreaseResponse);
}
