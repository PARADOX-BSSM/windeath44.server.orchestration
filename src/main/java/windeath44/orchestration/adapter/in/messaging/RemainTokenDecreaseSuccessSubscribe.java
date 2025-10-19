package windeath44.orchestration.adapter.in.messaging;

import com.chatbot.events.ChatEvent;
import com.example.user.avro.RemainTokenDecreaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import windeath44.orchestration.domain.port.in.RemainTokenDecreaseSuccessUseCase;

@Component
@RequiredArgsConstructor
public class RemainTokenDecreaseSuccessSubscribe {
    private final RemainTokenDecreaseSuccessUseCase remainTokenDecreaseSuccessUseCase;

    @KafkaListener(topics = "remain-token-decrease-response", groupId = "chatbot")
    public void listen(RemainTokenDecreaseResponse chatbotChatEvent) {
        remainTokenDecreaseSuccessUseCase.execute(chatbotChatEvent);
    }
}
