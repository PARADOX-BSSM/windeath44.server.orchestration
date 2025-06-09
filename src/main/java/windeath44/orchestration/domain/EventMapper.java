package windeath44.orchestration.domain;

import com.example.avro.MemorialAvroSchema;
import org.springframework.stereotype.Component;
import windeath44.orchestration.domain.model.Event;
import windeath44.orchestration.domain.model.MemorialEvent;

@Component
public class EventMapper {
  public MemorialEvent memorialEvent(MemorialAvroSchema memorialAvroSchema) {
    return new MemorialEvent();
  }

}