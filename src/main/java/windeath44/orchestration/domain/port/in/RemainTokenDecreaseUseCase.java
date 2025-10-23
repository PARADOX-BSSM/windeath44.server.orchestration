package windeath44.orchestration.domain.port.in;


import windeath44.server.chatbot.avro.ChatEvent;

public interface RemainTokenDecreaseUseCase {
    void  execute(ChatEvent chatEvent);
}