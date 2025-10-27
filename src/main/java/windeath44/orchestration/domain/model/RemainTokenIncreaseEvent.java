package windeath44.orchestration.domain.model;

import lombok.experimental.SuperBuilder;
import windeath44.server.user.avro.RemainTokenIncreaseResponse;

@SuperBuilder
public class RemainTokenIncreaseEvent extends Event<RemainTokenIncreaseResponse> {
}
