package windeath44.orchestration.application.usecase;

import com.example.avro.MemorialApplicationAvroSchema;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import windeath44.orchestration.domain.mapper.EventMapper;
import windeath44.orchestration.domain.model.MemorialApplicationEvent;
import windeath44.orchestration.domain.port.in.MemorialSageSuccessUseCase;
import windeath44.orchestration.domain.repository.EventRepository;

@Component
@RequiredArgsConstructor
public class MemorialSageSuccessUseCaseImpl implements MemorialSageSuccessUseCase {
  private final EventRepository eventRepository;
  private final EventMapper eventMapper;

  @Override
  public void execute(MemorialApplicationAvroSchema memorialAvroSchema) {
    MemorialApplicationEvent memorialApplicationEvent = eventMapper.memorialApplicationEvent(memorialAvroSchema);
    eventRepository.save(memorialApplicationEvent);
  }
}
