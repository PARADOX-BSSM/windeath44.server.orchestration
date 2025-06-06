package windeath44.orchestration.application.service;

import com.example.avro.MemorialAvroSchema;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import windeath44.orchestration.domain.port.out.MemorialCreateEventPublisher;

@Service
@RequiredArgsConstructor
public class MemorialService {
  private final MemorialCreateEventPublisher memorialCreateEventPublisher;

  public void create(MemorialAvroSchema memorialAvroSchema) {
    memorialCreateEventPublisher.publish(memorialAvroSchema);
  }
}
