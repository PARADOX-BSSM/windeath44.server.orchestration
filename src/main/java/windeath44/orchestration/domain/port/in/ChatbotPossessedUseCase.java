package windeath44.orchestration.domain.port.in;

import windeath44.server.chatbot.avro.ChatbotPossessedAvroSchema;

public interface ChatbotPossessedUseCase {
    void execute(ChatbotPossessedAvroSchema chatAvroSchema);
}
