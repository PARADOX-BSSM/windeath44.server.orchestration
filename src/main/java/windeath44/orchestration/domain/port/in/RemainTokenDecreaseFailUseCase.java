package windeath44.orchestration.domain.port.in;

import com.chatbot.events.ChatEvent;

public interface RemainTokenDecreaseFailUseCase {
    void execute(ChatEvent chatEvent);
}
