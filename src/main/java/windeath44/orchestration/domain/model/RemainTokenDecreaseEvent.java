package windeath44.orchestration.domain.model;

import com.example.user.avro.RemainTokenDecreaseResponse;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class RemainTokenDecreaseEvent extends Event<RemainTokenDecreaseResponse> {
}
