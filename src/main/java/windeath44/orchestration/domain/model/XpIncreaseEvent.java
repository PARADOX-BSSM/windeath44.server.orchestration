package windeath44.orchestration.domain.model;

import lombok.experimental.SuperBuilder;
import windeath44.server.user.avro.XpIncreaseResponse;

@SuperBuilder
public class XpIncreaseEvent extends Event<XpIncreaseResponse> {
}
