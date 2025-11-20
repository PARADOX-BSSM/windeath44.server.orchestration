package windeath44.orchestration.domain.model;

import lombok.experimental.SuperBuilder;
import windeath44.server.chatbot.avro.ChatAvroSchema;

@SuperBuilder
public class ChatbotChatEvent extends Event<ChatAvroSchema> {
}