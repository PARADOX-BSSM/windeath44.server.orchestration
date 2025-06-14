package windeath44.orchestration.application.service;

import lombok.RequiredArgsConstructor;
import org.apache.avro.specific.SpecificRecord;
import org.springframework.stereotype.Service;
import windeath44.orchestration.domain.port.out.MemorialApplicationEventPublisher;
import windeath44.orchestration.domain.model.type.MemorialAction;
import windeath44.orchestration.domain.model.type.MemorialApplicationAction;
import windeath44.orchestration.domain.port.out.MemorialEventPublisher;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemorialApplicationService {
  private final Map<MemorialApplicationAction, MemorialApplicationEventPublisher> memorialApplicationEventPublisher;

  public <T extends SpecificRecord> void execute(MemorialApplicationAction action, T avroSchema) {
    MemorialApplicationEventPublisher memorialApplicationPublisher = memorialApplicationEventPublisher.get(action);
    memorialApplicationPublisher.publish(avroSchema);
  }
}
