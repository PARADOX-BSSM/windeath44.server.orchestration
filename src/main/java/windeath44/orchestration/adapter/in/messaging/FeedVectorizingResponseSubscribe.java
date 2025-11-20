package windeath44.orchestration.adapter.in.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import windeath44.orchestration.domain.port.in.FeedVectorizingResponseUseCase;
import windeath44.server.memorial.avro.FeedAvroSchema;

@Component
@RequiredArgsConstructor
public class FeedVectorizingResponseSubscribe {
    private final FeedVectorizingResponseUseCase feedVectorizingResponseUseCase;

    @KafkaListener(topics = "memorial-vectorizing-response", groupId = "memorial")
    public void listen(FeedAvroSchema feedAvroSchema) {
        feedVectorizingResponseUseCase.execute(feedAvroSchema);
    }
}
