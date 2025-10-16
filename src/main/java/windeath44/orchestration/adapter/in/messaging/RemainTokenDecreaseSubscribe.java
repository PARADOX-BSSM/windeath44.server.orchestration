package windeath44.orchestration.adapter.in.messaging;

import com.chatbot.events.ChatEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import windeath44.orchestration.domain.port.in.RemainTokenDecreaseUseCase;

@Component
@RequiredArgsConstructor
public class RemainTokenDecreaseSubscribe {
    private final RemainTokenDecreaseUseCase remainTokenDecreaseUseCase;

    @KafkaListener(topics = "chatbot-chat-request", groupId = "chatbot")
    public void listen(ChatEvent chatEvent) {
        remainTokenDecreaseUseCase.execute(chatEvent);
    }
}
