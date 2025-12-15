package windeath44.orchestration.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import windeath44.orchestration.application.service.XpIncreaseService;
import windeath44.orchestration.domain.mapper.EventMapper;
import windeath44.orchestration.domain.model.Event;
import windeath44.orchestration.domain.model.MemorialUpdatedApplyEvent;
import windeath44.orchestration.domain.model.type.EventType;
import windeath44.orchestration.domain.model.type.XpRewardType;
import windeath44.orchestration.domain.port.in.MemorialApplyUpdatedUseCase;
import windeath44.orchestration.domain.repository.EventRepository;
import windeath44.server.memorial.avro.MemorialUpdatedApplyAvroSchema;
import windeath44.server.user.avro.XpIncreaseResponse;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MemorialApplyUpdatedUseCaseImpl implements MemorialApplyUpdatedUseCase {
    private final XpIncreaseService xpIncreaseService;
    private final EventMapper eventMapper;
    private final EventRepository eventRepository;

    @Override
    @Transactional
    public void execute(MemorialUpdatedApplyAvroSchema memorialUpdatedApplyAvroSchema) {
        publishXpIncreaseIfNeeded(memorialUpdatedApplyAvroSchema.getApplicantId());
        MemorialUpdatedApplyEvent memorialUpdatedApplyEvent = eventMapper.memorialApplicationApplyUpdatedEvent(memorialUpdatedApplyAvroSchema);
        eventRepository.save(memorialUpdatedApplyEvent);
    }

    private void publishXpIncreaseIfNeeded(String userId) {
        if (userId == null || userId.isBlank()) {
            return;
        }

        String aggregateId = XpRewardType.MEMORIAL_APPLY_UPDATE.aggregateId(userId);
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

        xpIncreaseService.publishApplyUpdateXp(userId);
    }
}
