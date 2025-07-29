package windeath44.orchestration.domain.model.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Topic {
  CHARACTER_MEMORIALIZING("character-memorializing-request"),
  MEMORIAL_CREATION("memorial-creation-request"),
  MEMORIAL_DELETION("memorial-deletion-request"),
  MEMORIAL_APPLICATION_CANCEL("memorial-application-cancel-request"),
  MEMORIAL_APPLICATION_APPROVED("memorial-application-approved-response")
  ;


  private final String topicName;
}
