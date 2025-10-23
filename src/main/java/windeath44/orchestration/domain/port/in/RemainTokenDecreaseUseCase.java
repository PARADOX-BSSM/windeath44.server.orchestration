package windeath44.orchestration.domain.port.in;


import windeath44.server.chatbot.avro.ChatAvroSchema;

public interface RemainTokenDecreaseUseCase {
    void  execute(ChatAvroSchema chatAvroSchema);
}