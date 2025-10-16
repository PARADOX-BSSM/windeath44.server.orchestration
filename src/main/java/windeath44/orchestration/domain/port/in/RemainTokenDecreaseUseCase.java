package windeath44.orchestration.domain.port.in;

import com.chatbot.events.ChatEvent;

public interface RemainTokenDecreaseUseCase {
    void  execute(ChatEvent chatEvent);
}