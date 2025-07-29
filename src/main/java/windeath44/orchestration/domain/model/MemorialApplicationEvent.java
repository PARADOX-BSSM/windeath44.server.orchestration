package windeath44.orchestration.domain.model;

import com.example.avro.MemorialApplicationAvroSchema;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class MemorialApplicationEvent extends Event<MemorialApplicationAvroSchema> {
}
