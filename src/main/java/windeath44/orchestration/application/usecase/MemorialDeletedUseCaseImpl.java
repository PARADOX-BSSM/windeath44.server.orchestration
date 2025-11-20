package windeath44.orchestration.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import windeath44.orchestration.application.service.MemorialVectorDeleteService;
import windeath44.orchestration.domain.mapper.EventMapper;
import windeath44.orchestration.domain.model.MemorialEvent;
import windeath44.orchestration.domain.port.in.MemorialDeletedUseCase;
import windeath44.orchestration.domain.repository.EventRepository;
import windeath44.server.memorial.avro.MemorialAvroSchema;

@Component
@RequiredArgsConstructor
public class MemorialDeletedUseCaseImpl implements MemorialDeletedUseCase {
    private final MemorialVectorDeleteService memorialVectorDeleteService;
    private final EventMapper eventMapper;
    private final EventRepository eventRepository;

    @Override
    @Transactional
    public void execute(MemorialAvroSchema memorialAvroSchema) {
        // 이벤트 저장
        MemorialEvent memorialEvent = eventMapper.memorialCompensateEvent(memorialAvroSchema);
        eventRepository.save(memorialEvent);

        // memorial-vector-delete-request 이벤트 발행
        memorialVectorDeleteService.execute(memorialAvroSchema);
    }
}
