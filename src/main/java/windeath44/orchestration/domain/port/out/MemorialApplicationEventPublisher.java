package windeath44.orchestration.domain.port.out;

import org.apache.avro.specific.SpecificRecord;
import windeath44.orchestration.domain.model.type.MemorialApplicationAction;

public interface MemorialApplicationEventPublisher {

  <T extends SpecificRecord> void publish(T memorialAvroSchema);
  MemorialApplicationAction getAction();
}
