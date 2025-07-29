package windeath44.orchestration.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import windeath44.orchestration.domain.model.type.MemorialApplicationAction;
import windeath44.orchestration.domain.port.out.MemorialApplicationEventPublisher;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
public class MemorialApplicationEventPublisherConfig {

  @Bean
  public Map<MemorialApplicationAction, MemorialApplicationEventPublisher> memorialApplicationEventPublisher(List<MemorialApplicationEventPublisher> memorialApplicationEventPublisherList) {
    return memorialApplicationEventPublisherList.stream()
            .collect(
                    Collectors.toMap(
                            MemorialApplicationEventPublisher::getAction,
                            Function.identity()
                    )
            );
  }
}
