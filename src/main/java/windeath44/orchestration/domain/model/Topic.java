package windeath44.orchestration.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Topic {
  CHARACTER_MEMORIALIZING("character-memorializing"),
  MEMORIAL_CREATION("memorial-creation"),
  MEMORIAL_DELETION("memorial-deletion")
  ;

  private final String topicName;
}
