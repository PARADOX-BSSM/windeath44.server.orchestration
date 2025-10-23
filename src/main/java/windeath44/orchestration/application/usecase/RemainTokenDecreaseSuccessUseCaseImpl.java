package windeath44.orchestration.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import windeath44.orchestration.domain.mapper.EventMapper;
import windeath44.orchestration.domain.model.RemainTokenDecreaseEvent;
import windeath44.orchestration.domain.port.in.RemainTokenDecreaseSuccessUseCase;
import windeath44.orchestration.domain.repository.EventRepository;
import windeath44.server.user.avro.RemainTokenDecreaseResponse;

@Component
@RequiredArgsConstructor
public class RemainTokenDecreaseSuccessUseCaseImpl implements RemainTokenDecreaseSuccessUseCase {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    @Override
    public void execute(RemainTokenDecreaseResponse remainTokenDecreaseResponse) {
        RemainTokenDecreaseEvent remainTokenDecreaseEvent = eventMapper.remainTokenDecreaseSuccessResponse(remainTokenDecreaseResponse);
        eventRepository.save(remainTokenDecreaseEvent);
    }
}
