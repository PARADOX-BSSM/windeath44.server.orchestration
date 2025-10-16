package windeath44.orchestration.application.usecase;

import com.chatbot.events.ChatEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import windeath44.orchestration.domain.mapper.EventMapper;
import windeath44.orchestration.domain.model.ChatbotChatEvent;
import windeath44.orchestration.domain.port.in.RemainTokenDecreaseSuccessUseCase;
import windeath44.orchestration.domain.repository.EventRepository;

@Component
@RequiredArgsConstructor
public class RemainTokenDecreaseSuccessUseCaseImpl implements RemainTokenDecreaseSuccessUseCase {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    @Override
    public void execute(ChatEvent chatEvent) {
        ChatbotChatEvent chatbotChatEvent = eventMapper.chatEventSuccessResponse(chatEvent);
        eventRepository.save(chatbotChatEvent);
    }
}
