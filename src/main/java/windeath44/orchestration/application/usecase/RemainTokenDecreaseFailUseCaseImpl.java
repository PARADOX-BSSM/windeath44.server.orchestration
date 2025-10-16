package windeath44.orchestration.application.usecase;

import com.chatbot.events.ChatEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import windeath44.orchestration.domain.mapper.EventMapper;
import windeath44.orchestration.domain.model.ChatbotChatEvent;
import windeath44.orchestration.domain.port.in.RemainTokenDecreaseFailUseCase;
import windeath44.orchestration.domain.repository.EventRepository;

@Component
@RequiredArgsConstructor
public class RemainTokenDecreaseFailUseCaseImpl implements RemainTokenDecreaseFailUseCase {
    private final EventMapper eventMapper;
    private final EventRepository eventRepository;

    @Override
    public void execute(ChatEvent chatEvent) {
        ChatbotChatEvent chatbotChatEvent = eventMapper.chatEventFailResponse(chatEvent);
        eventRepository.save(chatbotChatEvent);
    }
}
