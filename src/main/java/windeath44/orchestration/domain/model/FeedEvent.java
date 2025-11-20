package windeath44.orchestration.domain.model;

import lombok.experimental.SuperBuilder;
import windeath44.server.memorial.avro.FeedAvroSchema;

@SuperBuilder
public class FeedEvent extends Event<FeedAvroSchema>{

}
