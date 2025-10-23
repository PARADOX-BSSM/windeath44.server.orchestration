package windeath44.orchestration.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import windeath44.orchestration.domain.mapper.EventMapper;
import windeath44.orchestration.domain.model.ChatbotChatEvent;
import windeath44.orchestration.domain.port.in.RemainTokenDecreaseUseCase;
import windeath44.orchestration.domain.repository.EventRepository;
import windeath44.orchestration.global.kafka.KafkaProducer;
import windeath44.server.chatbot.avro.ChatEvent;

@Component
@RequiredArgsConstructor
public class RemainTokenDecreaseUseCaseImpl implements RemainTokenDecreaseUseCase {
    private final EventMapper eventMapper;
    private final EventRepository eventRepository;
    private final KafkaProducer kafkaProducer;

    @Override
    @Transactional
    public void execute(ChatEvent chatEvent) {
        // 이벤트 저장
        ChatbotChatEvent chatbotChatEvent = eventMapper.chatEvent(chatEvent);
        eventRepository.save(chatbotChatEvent);

        // remain-token-decrease-request 이벤트 발행
        kafkaProducer.send("remain-token-decrease-request", chatEvent);
    }
}
