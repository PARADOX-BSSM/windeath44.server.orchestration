package windeath44.orchestration.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import windeath44.orchestration.domain.mapper.EventMapper;
import windeath44.orchestration.domain.model.RemainTokenIncreaseEvent;
import windeath44.orchestration.domain.port.in.RemainTokenIncreaseFailResponseUseCase;
import windeath44.orchestration.domain.repository.EventRepository;
import windeath44.server.user.avro.RemainTokenIncreaseResponse;

@Component
@RequiredArgsConstructor
public class RemainTokenIncreaseFailResponseUseCaseImpl implements RemainTokenIncreaseFailResponseUseCase {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    @Override
    public void execute(RemainTokenIncreaseResponse remainTokenIncreaseResponse) {
        RemainTokenIncreaseEvent remainTokenIncreaseEvent = eventMapper.remainTokenIncreaseFailResponse(remainTokenIncreaseResponse);
        eventRepository.save(remainTokenIncreaseEvent);
    }
}
