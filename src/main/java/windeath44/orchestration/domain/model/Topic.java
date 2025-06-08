package windeath44.orchestration.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Topic {
  CHARACTER_MEMORIALIZING("character-memorializing-request"),
  MEMORIAL_CREATION("memorial-creation-request"),
  MEMORIAL_DELETION("memorial-deletion-request")
  ;

  private final String topicName;
}
