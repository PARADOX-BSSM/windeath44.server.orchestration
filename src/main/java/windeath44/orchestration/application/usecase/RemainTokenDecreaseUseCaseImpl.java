package windeath44.orchestration.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import windeath44.orchestration.application.service.RemainTokenDecreaseService;
import windeath44.orchestration.domain.mapper.EventMapper;
import windeath44.orchestration.domain.model.ChatbotChatEvent;
import windeath44.orchestration.domain.port.in.RemainTokenDecreaseUseCase;
import windeath44.orchestration.domain.repository.EventRepository;
import windeath44.server.chatbot.avro.ChatAvroSchema;

@Component
@RequiredArgsConstructor
public class RemainTokenDecreaseUseCaseImpl implements RemainTokenDecreaseUseCase {
    private final RemainTokenDecreaseService remainTokenDecreaseService;
    private final EventMapper eventMapper;
    private final EventRepository eventRepository;

    @Override
    @Transactional
    public void execute(ChatAvroSchema chatAvroSchema) {
        // 이벤트 저장
        ChatbotChatEvent chatbotChatEvent = eventMapper.chatEvent(chatAvroSchema);
        eventRepository.save(chatbotChatEvent);

        // remain-token-decrease-request 이벤트 발행
        remainTokenDecreaseService.execute(chatAvroSchema);
    }
}
