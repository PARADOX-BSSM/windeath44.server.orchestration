package windeath44.orchestration.application.usecase;

import com.example.user.avro.RemainTokenIncreaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import windeath44.orchestration.domain.mapper.EventMapper;
import windeath44.orchestration.domain.model.RemainTokenIncreaseEvent;
import windeath44.orchestration.domain.port.in.RemainTokenIncreaseResponseUseCase;
import windeath44.orchestration.domain.repository.EventRepository;

@Component
@RequiredArgsConstructor
public class RemainTokenIncreaseResponseUseCaseImpl implements RemainTokenIncreaseResponseUseCase {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    @Override
    public void execute(RemainTokenIncreaseResponse remainTokenIncreaseResponse) {
        RemainTokenIncreaseEvent remainTokenIncreaseEvent = eventMapper.remainTokenIncreaseResponse(remainTokenIncreaseResponse);
        eventRepository.save(remainTokenIncreaseEvent);
    }
}
