package windeath44.orchestration.application.usecase;

import com.example.avro.CharacterAvroSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import windeath44.orchestration.application.service.MemorialApplicationService;
import windeath44.orchestration.domain.mapper.EventMapper;
import windeath44.orchestration.domain.model.CharacterEvent;
import windeath44.orchestration.domain.model.type.MemorialApplicationAction;
import windeath44.orchestration.domain.repository.EventRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MemorialApplicationApproveUseCaseImplTest {

    @Mock
    private EventMapper eventMapper;

    @Mock
    private EventRepository eventRepository;

    @Mock
    private MemorialApplicationService memorialApplicationService;

    @InjectMocks
    private MemorialApplicationApproveUseCaseImpl memorialApplicationApproveUseCase;

    private CharacterAvroSchema characterAvroSchema;
    private CharacterEvent characterEvent;

    @BeforeEach
    void setUp() {
        // Setup test data
        characterAvroSchema = CharacterAvroSchema.newBuilder()
                .setCharacterId("123")
                .setName("Test Character")
                .setContent("Test Content")
                .setDeathReason("Test Death Reason")
                .setState(1L)
                .build();

        characterEvent = CharacterEvent.builder()
                .aggregateId("character-123")
                .aggregateType("CHARACTER")
                .eventData(characterAvroSchema)
                .build();
    }

    @Test
    @DisplayName("추모관 신청 승인 테스트")
    void executeTest() {
        // Given
        doNothing().when(memorialApplicationService).execute(eq(MemorialApplicationAction.APPROVE), any(CharacterAvroSchema.class));
        when(eventMapper.characterEvent(any(CharacterAvroSchema.class))).thenReturn(characterEvent);
        when(eventRepository.save(any(CharacterEvent.class))).thenReturn(characterEvent);

        // When
        memorialApplicationApproveUseCase.execute(characterAvroSchema);

        // Then
        verify(memorialApplicationService, times(1)).execute(MemorialApplicationAction.APPROVE, characterAvroSchema);
        verify(eventMapper, times(1)).characterEvent(characterAvroSchema);
        verify(eventRepository, times(1)).save(characterEvent);
    }
}
