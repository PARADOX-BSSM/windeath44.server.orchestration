package windeath44.orchestration.domain;

import com.example.avro.CharacterAvroSchema;
import com.example.avro.MemorialApplicationAvroSchema;
import com.example.avro.MemorialAvroSchema;
import org.springframework.stereotype.Component;
import windeath44.orchestration.domain.model.CharacterEvent;
import windeath44.orchestration.domain.model.MemorialApplicationEvent;
import windeath44.orchestration.domain.model.MemorialEvent;
import windeath44.orchestration.domain.model.type.EventType;

@Component
public class EventMapper {
  public MemorialEvent memorialEvent(MemorialAvroSchema memorialAvroSchema) {
    String aggregateId = "memorial-" + memorialAvroSchema.getMemorialId();
    String aggregateType = "MEMORIAL";
    EventType eventType = EventType.MEMORIAL_CREATED;

    return MemorialEvent.builder()
            .aggregateId(aggregateId)
            .aggregateType(aggregateType)
            .eventType(eventType)
            .eventData(memorialAvroSchema)
            .build();
  }

  public MemorialEvent memorialCompensateEvent(MemorialAvroSchema memorialAvroSchema) {
    String aggregateId = "memorial-" + memorialAvroSchema.getMemorialId();
    String aggregateType = "MEMORIAL";
    EventType eventType = EventType.MEMORIAL_DELETED;

    return MemorialEvent.builder()
            .aggregateId(aggregateId)
            .aggregateType(aggregateType)
            .eventType(eventType)
            .eventData(memorialAvroSchema)
            .build();
  }

  public MemorialApplicationEvent memorialApplicationEvent(MemorialApplicationAvroSchema memorialApplicationAvroSchema) {
    String aggregateId = "memorial-application-" + memorialApplicationAvroSchema.getMemorialApplicationId();
    String aggregateType = "MEMORIAL_APPLICATION";
    EventType eventType = EventType.MEMORIAL_APPLICATION_APPROVED;

    return MemorialApplicationEvent.builder()
            .aggregateId(aggregateId)
            .aggregateType(aggregateType)
            .eventType(eventType)
            .eventData(memorialApplicationAvroSchema)
            .build();
  }


  public CharacterEvent characterEvent(CharacterAvroSchema characterAvroSchema) {
    String aggregateId = "character-" + characterAvroSchema.getCharacterId();
    String aggregateType = "ANIME";
    EventType eventType = EventType.CHARACTER_MEMORIALIZED;

    return CharacterEvent.builder()
            .aggregateId(aggregateId)
            .aggregateType(aggregateType)
            .eventType(eventType)
            .eventData(characterAvroSchema)
            .build();
  }
}