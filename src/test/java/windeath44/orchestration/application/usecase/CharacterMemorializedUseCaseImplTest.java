package windeath44.orchestration.application.usecase;

import com.example.avro.CharacterAvroSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import windeath44.orchestration.domain.mapper.EventMapper;
import windeath44.orchestration.domain.model.CharacterEvent;
import windeath44.orchestration.domain.repository.EventRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CharacterMemorializedUseCaseImplTest {

    @Mock
    private EventMapper eventMapper;

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private CharacterMemorializedUseCaseImpl characterMemorializedUseCase;

    private CharacterAvroSchema characterAvroSchema;
    private CharacterEvent characterEvent;

    @BeforeEach
    void setUp() {
        // Setup test data
        characterAvroSchema = CharacterAvroSchema.newBuilder()
                .setCharacterId("character-123")
                .setName("Test Character")
                .setContent("Test Content")
                .setDeathReason("Test Death Reason")
                .setState(1L)
                .build();

        characterEvent = CharacterEvent.builder()
                .aggregateId("character-character-123")
                .aggregateType("ANIME")
                .eventData(characterAvroSchema)
                .build();
    }

    @Test
    @DisplayName("캐릭터 추모 이벤트 처리 테스트")
    void executeTest() {
        // Given
        when(eventMapper.characterEvent(any(CharacterAvroSchema.class))).thenReturn(characterEvent);

        // When
        characterMemorializedUseCase.execute(characterAvroSchema);

        // Then
        verify(eventMapper, times(1)).characterEvent(characterAvroSchema);
    }
}