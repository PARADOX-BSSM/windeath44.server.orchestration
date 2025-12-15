package windeath44.orchestration.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import windeath44.orchestration.application.service.XpIncreaseService;
import windeath44.orchestration.domain.mapper.EventMapper;
import windeath44.orchestration.domain.model.ChatbotPossessedEvent;
import windeath44.orchestration.domain.model.type.XpRewardType;
import windeath44.orchestration.domain.port.in.ChatbotPossessedUseCase;
import windeath44.orchestration.domain.repository.EventRepository;
import windeath44.server.chatbot.avro.ChatbotPossessedAvroSchema;

@Component
@RequiredArgsConstructor
public class ChatbotPossessedUseCaseImpl implements ChatbotPossessedUseCase {
    private final XpIncreaseService xpIncreaseService;
    private final EventMapper eventMapper;
    private final EventRepository eventRepository;

    @Override
    @Transactional
    public void execute(ChatbotPossessedAvroSchema chatbotPossessedAvroSchema) {
        if (chatbotPossessedAvroSchema.getApplicantId() != null && !chatbotPossessedAvroSchema.getApplicantId().isBlank()) {
            xpIncreaseService.publishChatbotPossessedXp(chatbotPossessedAvroSchema.getApplicantId());
        }

        ChatbotPossessedEvent event = eventMapper.chatbotPossessedEvent(chatbotPossessedAvroSchema);
        eventRepository.save(event);
    }
}
