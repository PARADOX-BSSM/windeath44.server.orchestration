package windeath44.orchestration.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import windeath44.orchestration.domain.mapper.EventMapper;
import windeath44.orchestration.domain.model.XpIncreaseEvent;
import windeath44.orchestration.domain.port.in.XpIncreaseResponseUseCase;
import windeath44.orchestration.domain.repository.EventRepository;
import windeath44.server.user.avro.XpIncreaseResponse;

@Component
@RequiredArgsConstructor
public class XpIncreaseResponseUseCaseImpl implements XpIncreaseResponseUseCase {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    @Override
    public void execute(XpIncreaseResponse xpIncreaseResponse) {
        XpIncreaseEvent xpIncreaseEvent = eventMapper.xpIncreaseResponse(xpIncreaseResponse);
        eventRepository.save(xpIncreaseEvent);
    }
}
