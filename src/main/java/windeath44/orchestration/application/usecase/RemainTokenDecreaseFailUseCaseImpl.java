package windeath44.orchestration.application.usecase;

import com.example.user.avro.RemainTokenDecreaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import windeath44.orchestration.domain.mapper.EventMapper;
import windeath44.orchestration.domain.model.RemainTokenDecreaseEvent;
import windeath44.orchestration.domain.port.in.RemainTokenDecreaseFailUseCase;
import windeath44.orchestration.domain.repository.EventRepository;

@Component
@RequiredArgsConstructor
public class RemainTokenDecreaseFailUseCaseImpl implements RemainTokenDecreaseFailUseCase {
    private final EventMapper eventMapper;
    private final EventRepository eventRepository;

    @Override
    public void execute(RemainTokenDecreaseResponse remainTokenDecreaseResponse) {
        RemainTokenDecreaseEvent remainTokenDecreaseEvent = eventMapper.remainTokenDecreaseFailResponse(remainTokenDecreaseResponse);
        eventRepository.save(remainTokenDecreaseEvent);
    }
}
