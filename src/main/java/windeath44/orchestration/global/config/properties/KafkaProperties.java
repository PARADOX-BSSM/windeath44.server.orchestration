package windeath44.orchestration.global.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("kafka")
@Getter
@Setter
public class KafkaProperties {
  private String bootstrapServers;
  private String schemaRegistryUrl;

  private Consumer consumer;

  @Getter
  @Setter
  public static class Consumer {
    private String auto_offset_reset;

  }

}
