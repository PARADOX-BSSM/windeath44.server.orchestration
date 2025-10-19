package windeath44.orchestration.domain.port.in;

import com.example.user.avro.RemainTokenDecreaseResponse;

public interface RemainTokenDecreaseFailUseCase {
    void execute(RemainTokenDecreaseResponse remainTokenDecreaseResponse);
}
