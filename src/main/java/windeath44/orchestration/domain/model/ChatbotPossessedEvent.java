package windeath44.orchestration.domain.model;

import lombok.experimental.SuperBuilder;
import windeath44.server.chatbot.avro.ChatbotPossessedAvroSchema;

@SuperBuilder
public class ChatbotPossessedEvent extends Event<ChatbotPossessedAvroSchema> {
}
