package windeath44.orchestration.domain.model;

import lombok.experimental.SuperBuilder;
import windeath44.server.user.avro.RemainTokenDecreaseResponse;

@SuperBuilder
public class RemainTokenDecreaseEvent extends Event<RemainTokenDecreaseResponse> {
}
