package windeath44.orchestration.domain.port.in;

import windeath44.server.memorial.avro.FeedAvroSchema;

public interface FeedVectorizingResponseUseCase {
    void execute(FeedAvroSchema feedAvroSchema);
}
