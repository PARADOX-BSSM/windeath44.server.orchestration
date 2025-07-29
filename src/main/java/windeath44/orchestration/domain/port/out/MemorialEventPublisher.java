package windeath44.orchestration.domain.port.out;

import com.example.avro.MemorialApplicationAvroSchema;
import com.example.avro.MemorialAvroSchema;
import org.apache.avro.specific.SpecificRecord;
import windeath44.orchestration.domain.model.type.MemorialAction;

public interface MemorialEventPublisher {
  <T extends SpecificRecord> void publish(T memorialAvroSchema);
  MemorialAction getAction();
}
