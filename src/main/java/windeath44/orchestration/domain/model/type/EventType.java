package windeath44.orchestration.domain.model.type;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EventType {
  MEMORIAL_APPLICATION_APPROVED,
  MEMORIAL_CREATED,
  MEMORIAL_DELETED,
  CHARACTER_MEMORIALIZED,
  ;
}
