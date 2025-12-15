package windeath44.orchestration.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import windeath44.orchestration.domain.model.Event;
import windeath44.orchestration.domain.model.type.EventType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EventRepository extends MongoRepository<Event, Long> {
    List<Event> findByAggregateIdAndEventTypeAndOccurredAtAfter(String aggregateId, EventType eventType, LocalDateTime occurredAt);
    Optional<Event> findFirstByAggregateIdAndEventTypeOrderByOccurredAtDesc(String aggregateId, EventType eventType);
}
