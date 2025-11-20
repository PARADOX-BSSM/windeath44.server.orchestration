package windeath44.orchestration.adapter.in.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import windeath44.orchestration.domain.port.in.RemainTokenIncreaseFailResponseUseCase;
import windeath44.server.user.avro.RemainTokenIncreaseResponse;

@Component
@RequiredArgsConstructor
public class RemainTokenIncreaseFailResponseSubscribe {
    private final RemainTokenIncreaseFailResponseUseCase remainTokenIncreaseFailResponseUseCase;

    @KafkaListener(topics = "remain-token-increase-fail-response", groupId = "user")
    public void listen(RemainTokenIncreaseResponse remainTokenIncreaseResponse) {
        remainTokenIncreaseFailResponseUseCase.execute(remainTokenIncreaseResponse);
    }
}
