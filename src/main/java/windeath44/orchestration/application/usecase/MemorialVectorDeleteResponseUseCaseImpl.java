package windeath44.orchestration.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import windeath44.orchestration.domain.mapper.EventMapper;
import windeath44.orchestration.domain.model.MemorialEvent;
import windeath44.orchestration.domain.port.in.MemorialVectorDeleteResponseUseCase;
import windeath44.orchestration.domain.repository.EventRepository;
import windeath44.server.memorial.avro.MemorialAvroSchema;

@Component
@RequiredArgsConstructor
public class MemorialVectorDeleteResponseUseCaseImpl implements MemorialVectorDeleteResponseUseCase {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    @Override
    public void execute(MemorialAvroSchema memorialAvroSchema) {
        MemorialEvent memorialEvent = eventMapper.memorialVectorDeleteResponse(memorialAvroSchema);
        eventRepository.save(memorialEvent);
    }
}
