package windeath44.orchestration.adapter.in.messaging;

import com.chatbot.events.ChatEvent;
import com.example.user.avro.RemainTokenDecreaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import windeath44.orchestration.domain.port.in.RemainTokenDecreaseFailUseCase;

@Component
@RequiredArgsConstructor
public class RemainTokenDecreaseFailSubscribe {
    private final RemainTokenDecreaseFailUseCase remainTokenDecreaseFailUseCase;

    @KafkaListener(topics = "remain-token-decrease-fail-response", groupId = "chatbot")
    public void listen(RemainTokenDecreaseResponse remainTokenDecreaseResponse) {
        remainTokenDecreaseFailUseCase.execute(remainTokenDecreaseResponse);
    }
}
