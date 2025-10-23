package windeath44.orchestration.application.usecase;

import com.example.user.avro.RemainTokenIncreaseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import windeath44.orchestration.domain.mapper.EventMapper;
import windeath44.orchestration.domain.model.MemorialBowedEvent;
import windeath44.orchestration.domain.port.in.MemorialBowedUseCase;
import windeath44.orchestration.domain.repository.EventRepository;
import windeath44.orchestration.global.kafka.KafkaProducer;
import windeath44.server.memorial.avro.MemorialBowedAvroSchema;

@Component
@RequiredArgsConstructor
public class MemorialBowedUseCaseImpl implements MemorialBowedUseCase {
    private final EventMapper eventMapper;
    private final EventRepository eventRepository;
    private final KafkaProducer kafkaProducer;

    @Override
    @Transactional
    public void execute(MemorialBowedAvroSchema memorialBowedAvroSchema) {
        // 이벤트 저장
        MemorialBowedEvent memorialBowedEvent = eventMapper.memorialBowedEvent(memorialBowedAvroSchema);
        eventRepository.save(memorialBowedEvent);

        // RemainTokenIncreaseRequest 생성
        RemainTokenIncreaseRequest remainTokenIncreaseRequest = RemainTokenIncreaseRequest.newBuilder()
                .setUserId(memorialBowedAvroSchema.getWriterId())
                .setTokenAmount(memorialBowedAvroSchema.getMemorialBow())
                .setReason("memorial-bowed")
                .build();

        // remain-token-increase-request 이벤트 발행
        kafkaProducer.send("remain-token-increase-request", remainTokenIncreaseRequest);
    }
}
