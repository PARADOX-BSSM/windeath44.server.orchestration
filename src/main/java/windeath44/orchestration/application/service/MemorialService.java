package windeath44.orchestration.application.service;

import com.example.avro.MemorialAvroSchema;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import windeath44.orchestration.domain.model.type.MemorialAction;
import windeath44.orchestration.domain.port.out.MemorialEventPublisher;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemorialService {
  private final Map<MemorialAction, MemorialEventPublisher> memorialEventPublisher;

  public void execute(MemorialAction action, MemorialAvroSchema memorialAvroSchema) {
    MemorialEventPublisher memorialPublisher = memorialEventPublisher.get(action);
    memorialPublisher.publish(memorialAvroSchema);
  }
}
