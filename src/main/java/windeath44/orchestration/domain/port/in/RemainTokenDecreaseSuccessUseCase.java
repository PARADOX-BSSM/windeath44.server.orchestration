package windeath44.orchestration.domain.port.in;

import com.chatbot.events.ChatEvent;

public interface RemainTokenDecreaseSuccessUseCase {
    void execute(ChatEvent chatEvent);
}
