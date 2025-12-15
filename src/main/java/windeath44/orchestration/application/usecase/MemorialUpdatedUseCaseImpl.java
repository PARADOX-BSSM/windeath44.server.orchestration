package windeath44.orchestration.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import windeath44.orchestration.application.service.MemorialVectorizingService;
import windeath44.orchestration.application.service.XpIncreaseService;
import windeath44.orchestration.domain.mapper.EventMapper;
import windeath44.orchestration.domain.model.MemorialEvent;
import windeath44.orchestration.domain.model.type.XpRewardType;
import windeath44.orchestration.domain.port.in.MemorialUpdatedUseCase;
import windeath44.orchestration.domain.repository.EventRepository;
import windeath44.server.memorial.avro.MemorialAvroSchema;

@Component
@RequiredArgsConstructor
public class MemorialUpdatedUseCaseImpl implements MemorialUpdatedUseCase {
    private final MemorialVectorizingService memorialVectorizingService;
    private final XpIncreaseService xpIncreaseService;
    private final EventMapper eventMapper;
    private final EventRepository eventRepository;

    @Override
    @Transactional
    public void execute(MemorialAvroSchema memorialAvroSchema) {
        MemorialEvent memorialEvent = eventMapper.memorialEvent(memorialAvroSchema);
        eventRepository.save(memorialEvent);

        memorialVectorizingService.execute(memorialAvroSchema);
        publishXpIncrease(memorialAvroSchema.getWriterId());
    }

    private void publishXpIncrease(String userId) {
        if (userId == null || userId.isBlank()) {
            return;
        }

        xpIncreaseService.publishMemorialUpdateXp(userId);
    }
}
