package windeath44.orchestration.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import windeath44.orchestration.application.service.MemorialVectorizingService;
import windeath44.orchestration.domain.mapper.EventMapper;
import windeath44.orchestration.domain.model.MemorialEvent;
import windeath44.orchestration.domain.port.in.MemorialUpdatedUseCase;
import windeath44.orchestration.domain.repository.EventRepository;
import windeath44.server.memorial.avro.MemorialAvroSchema;

@Component
@RequiredArgsConstructor
public class MemorialUpdatedUseCaseImpl implements MemorialUpdatedUseCase {
    private final MemorialVectorizingService memorialVectorizingService;
    private final EventMapper eventMapper;
    private final EventRepository eventRepository;

    @Override
    @Transactional
    public void execute(MemorialAvroSchema memorialAvroSchema) {
        // 이벤트 저장
        MemorialEvent memorialEvent = eventMapper.memorialEvent(memorialAvroSchema);
        eventRepository.save(memorialEvent);

        // memorial-vectorizing-request 이벤트 발행
        memorialVectorizingService.execute(memorialAvroSchema);
    }
}
