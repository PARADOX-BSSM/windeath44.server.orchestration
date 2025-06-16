package windeath44.orchestration.application.service;

import com.example.avro.MemorialApplicationAvroSchema;
import lombok.RequiredArgsConstructor;

import org.apache.avro.specific.SpecificRecord;
import org.springframework.stereotype.Service;
import windeath44.orchestration.domain.model.type.MemorialAction;
import windeath44.orchestration.domain.port.out.MemorialEventPublisher;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemorialService {
  private final Map<MemorialAction, MemorialEventPublisher> memorialEventPublisher;

  public <T extends SpecificRecord> void execute(MemorialAction action, T avroSchema) {
    MemorialEventPublisher memorialPublisher = memorialEventPublisher.get(action);
    memorialPublisher.publish(avroSchema);
  }
}
