package windeath44.orchestration.adapter.in.messaging;


import com.example.avro.MemorialAvroSchema;
import com.google.common.eventbus.Subscribe;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import windeath44.orchestration.domain.port.in.MemorialCreateUseCase