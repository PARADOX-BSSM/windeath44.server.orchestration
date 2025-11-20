package windeath44.orchestration.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import windeath44.orchestration.domain.mapper.EventMapper;
import windeath44.orchestration.domain.model.FeedEvent;
import windeath44.orchestration.domain.port.in.FeedVectorizingResponseUseCase;
import windeath44.orchestration.domain.repository.EventRepository;
import windeath44.server.memorial.avro.FeedAvroSchema;

@Component
@RequiredArgsConstructor
public class FeedVectorizingResponseUseCaseImpl implements FeedVectorizingResponseUseCase {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    @Override
    public void execute(FeedAvroSchema feedAvroSchema) {
        FeedEvent feedEvent = eventMapper.feedVectorizingResponse(feedAvroSchema);
        eventRepository.save(feedEvent);
    }
}
