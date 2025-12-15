package windeath44.orchestration.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import windeath44.orchestration.application.service.RemainTokenIncreaseService;
import windeath44.orchestration.application.service.XpIncreaseService;
import windeath44.orchestration.domain.mapper.EventMapper;
import windeath44.orchestration.domain.model.Event;
import windeath44.orchestration.domain.model.MemorialBowedEvent;
import windeath44.orchestration.domain.model.type.EventType;
import windeath44.orchestration.domain.model.type.XpRewardType;
import windeath44.orchestration.domain.port.in.MemorialBowedUseCase;
import windeath44.orchestration.domain.repository.EventRepository;
import windeath44.server.memorial.avro.MemorialBowedAvroSchema;
import windeath44.server.user.avro.XpIncreaseResponse;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MemorialBowedUseCaseImpl implements MemorialBowedUseCase {
    private final RemainTokenIncreaseService remainTokenIncreaseService;
    private final XpIncreaseService xpIncreaseService;
    private final EventMapper eventMapper;
    private final EventRepository eventRepository;

    @Override
    @Transactional
    public void execute(MemorialBowedAvroSchema memorialBowedAvroSchema) {
        remainTokenIncreaseService.execute(memorialBowedAvroSchema);
        publishXpIncreaseIfNeeded(memorialBowedAvroSchema.getWriterId());
        MemorialBowedEvent memorialBowedEvent = eventMapper.memorialBowedEvent(memorialBowedAvroSchema);
        eventRepository.save(memorialBowedEvent);
    }

    private void publishXpIncreaseIfNeeded(String userId) {
        if (userId == null || userId.isBlank()) {
            return;
        }

        String aggregateId = XpRewardType.MEMORIAL_BOW.aggregateId(userId);
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

        xpIncreaseService.publishBowXp(userId);
    }
}
