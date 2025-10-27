package windeath44.orchestration.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import windeath44.orchestration.application.service.RemainTokenIncreaseService;
import windeath44.orchestration.domain.mapper.EventMapper;
import windeath44.orchestration.domain.model.MemorialBowedEvent;
import windeath44.orchestration.domain.port.in.MemorialBowedUseCase;
import windeath44.orchestration.domain.repository.EventRepository;
import windeath44.server.memorial.avro.MemorialBowedAvroSchema;

@Component
@RequiredArgsConstructor
public class MemorialBowedUseCaseImpl implements MemorialBowedUseCase {
    private final RemainTokenIncreaseService remainTokenIncreaseService;
    private final EventMapper eventMapper;
    private final EventRepository eventRepository;

    @Override
    @Transactional
    public void execute(MemorialBowedAvroSchema memorialBowedAvroSchema) {
        remainTokenIncreaseService.execute(memorialBowedAvroSchema);
        MemorialBowedEvent memorialBowedEvent = eventMapper.memorialBowedEvent(memorialBowedAvroSchema);
        eventRepository.save(memorialBowedEvent);
    }
}

