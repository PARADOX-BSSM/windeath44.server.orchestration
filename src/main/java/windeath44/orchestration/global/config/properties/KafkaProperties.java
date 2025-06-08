package windeath44.orchestration.global.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("kafka")
@Setter
@Getter
public class KafkaProperties {
  private String bootstrapServers;
  private String schemaRegistryUrl;

  private Consumer consumer;

  @Setter
  @Getter
  public static class Consumer {
    private String autoOffsetReset;
  }

}
