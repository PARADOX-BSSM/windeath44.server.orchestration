package windeath44.orchestration.adapter.in.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import windeath44.orchestration.domain.port.in.ChatbotPossessedUseCase;
import windeath44.server.chatbot.avro.ChatbotPossessedAvroSchema;

@Component
@RequiredArgsConstructor
public class ChatbotPossessedSubscribe {
    private final ChatbotPossessedUseCase chatbotPossessedUseCase;

    @KafkaListener(topics = "chatbot-possessed-request", groupId = "chatbot")
    public void listen(ChatbotPossessedAvroSchema chatbotPossessedAvroSchema) {
        chatbotPossessedUseCase.execute(chatbotPossessedAvroSchema);
    }
}
