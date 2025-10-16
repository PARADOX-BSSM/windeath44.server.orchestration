package windeath44.orchestration.domain.port.in;

import com.example.user.avro.RemainTokenDecreaseResponse;

public interface RemainTokenDecreaseSuccessUseCase {
    void execute(RemainTokenDecreaseResponse remainTokenDecreaseResponse);
}
