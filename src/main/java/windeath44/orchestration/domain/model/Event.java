package windeath44.orchestration.domain.model;


import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import windeath44.orchestration.domain.model.type.EventType;

import java.time.LocalDateTime;

@Document(collection = "events")
@SuperBuilder
public abstract class Event<T> {
  @Id
  private String eventId;
  private String aggregateId;
  private String aggregateType;
  private EventType eventType;
  private T eventData;
  @CreatedDate
  private LocalDateTime occurredAt;
  @Version
  private int version;

  public Event(String aggregateId, String aggregateType, EventType eventType, T eventData) {
    this.aggregateId = aggregateId;
    this.aggregateType = aggregateType;
    this.eventType = eventType;
    this.eventData = eventData;
    this.occurredAt = LocalDateTime.now();
  }
}
