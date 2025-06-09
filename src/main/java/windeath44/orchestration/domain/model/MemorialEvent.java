package windeath44.orchestration.domain.model;

import com.example.avro.MemorialAvroSchema;
import windeath44.orchestration.domain.model.type.EventType;

public class MemorialEvent extends Event<MemorialAvroSchema>{
  public MemorialEvent(String aggregateId, String aggregateType, EventType eventType, MemorialAvroSchema eventData) {
    super(aggregateId, aggregateType, eventType, eventData);
  }
}