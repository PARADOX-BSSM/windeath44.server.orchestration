package windeath44.orchestration.adapter.in.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import windeath44.orchestration.domain.port.in.RemainTokenIncreaseResponseUseCase;
import windeath44.server.user.avro.RemainTokenIncreaseResponse;

@Component
@RequiredArgsConstructor
public class RemainTokenIncreaseResponseSubscribe {
    private final RemainTokenIncreaseResponseUseCase remainTokenIncreaseResponseUseCase;

    @KafkaListener(topics = "remain-token-increase-response", groupId = "user")
    public void listen(RemainTokenIncreaseResponse remainTokenIncreaseResponse) {
        remainTokenIncreaseResponseUseCase.execute(remainTokenIncreaseResponse);
    }
}
