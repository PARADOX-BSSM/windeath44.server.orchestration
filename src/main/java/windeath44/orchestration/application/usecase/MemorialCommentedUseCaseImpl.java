package windeath44.orchestration.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import windeath44.orchestration.application.service.XpIncreaseService;
import windeath44.orchestration.domain.mapper.EventMapper;
import windeath44.orchestration.domain.model.Event;
import windeath44.orchestration.domain.model.MemorialCommentedEvent;
import windeath44.orchestration.domain.model.type.EventType;
import windeath44.orchestration.domain.model.type.XpRewardType;
import windeath44.orchestration.domain.port.in.MemorialCommentedUseCase;
import windeath44.orchestration.domain.repository.EventRepository;
import windeath44.server.memorial.avro.MemorialCommentedAvroSchema;
import windeath44.server.user.avro.XpIncreaseResponse;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MemorialCommentedUseCaseImpl implements MemorialCommentedUseCase {
    private final XpIncreaseService xpIncreaseService;
    private final EventMapper eventMapper;
    private final EventRepository eventRepository;

    @Override
    @Transactional
    public void execute(MemorialCommentedAvroSchema memorialCommentedAvroSchema) {
        publishXpIncreaseIfNeeded(memorialCommentedAvroSchema.getCommenterId());
        MemorialCommentedEvent memorialCommentedEvent = eventMapper.memorialCommentedEvent(memorialCommentedAvroSchema);
        eventRepository.save(memorialCommentedEvent);
    }

    private void publishXpIncreaseIfNeeded(String userId) {
        if (userId == null || userId.isBlank()) {
            return;
        }

        String aggregateId = XpRewardType.MEMORIAL_COMMENT.aggregateId(userId);
        LocalDateTime oneDayAgo = LocalDateTime.now().minusDays(1);
        Optional<Event> latestXpEvent = eventRepository.findFirstByAggregateIdAndEventTypeOrderByOccurredAtDesc(
                aggregateId, EventType.XP_INCREASE_RESPONSE
        );

        boolean alreadyRewarded = latestXpEvent
                .filter(event -> event.getOccurredAt() != null && event.getOccurredAt().isAfter(oneDayAgo))
                .map(Event::getEventData)
                .filter(XpIncreaseResponse.class::isInstance)
                .map(XpIncreaseResponse.class::cast)
                .map(XpIncreaseResponse::getSuccess)
                .orElse(false);

        if (alreadyRewarded) {
            return;
        }

        xpIncreaseService.publishCommentXp(userId);
    }
}
