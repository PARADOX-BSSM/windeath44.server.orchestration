package windeath44.orchestration.domain.model.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Topic {
  MEMORIAL_CREATION("memorial-creation-request"),
  MEMORIAL_APPLICATION_CANCEL("memorial-application-cancel-request"),
  MEMORIAL_APPLICATION_APPROVED("memorial-application-approved-response"),
  REMAIN_TOKEN_DECREASE("remain-token-decrease-request"),
  REMAIN_TOKEN_INCREASE("remain-token-increase-request")
  ;


  private final String topicName;
}
