package windeath44.orchestration.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import windeath44.orchestration.domain.model.MemorialAction;
import windeath44.orchestration.domain.port.out.MemorialEventPublisher;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
public class MemorialEventPublisherConfig {

  @Bean
  public Map<MemorialAction, MemorialEventPublisher> memorialEventPublisher(List<MemorialEventPublisher> memorialEventPublisherList) {
    return memorialEventPublisherList.stream()
            .collect(
                    Collectors.toMap(
                            MemorialEventPublisher::getAction,
                            Function.identity()
                    )
            );
  }
}
