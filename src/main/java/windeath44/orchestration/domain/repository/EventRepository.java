package windeath44.orchestration.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import windeath44.orchestration.domain.model.Event;

public interface EventRepository extends MongoRepository<Event, Long> {
}
