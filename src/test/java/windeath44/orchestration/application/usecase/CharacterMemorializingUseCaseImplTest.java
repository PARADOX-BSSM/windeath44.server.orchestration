package windeath44.orchestration.application.usecase;

import com.example.avro.MemorialAvroSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import windeath44.orchestration.application.service.AnimeService;
import windeath44.orchestration.domain.mapper.EventMapper;
import windeath44.orchestration.domain.model.MemorialEvent;
import windeath44.orchestration.domain.repository.EventRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CharacterMemorializingUseCaseImplTest {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private AnimeService animeService;

    @Mock
    private EventMapper eventMapper;

    @InjectMocks
    private CharacterMemorializingUseCaseImpl characterMemorializingUseCase;

    private MemorialAvroSchema memorialAvroSchema;
    private MemorialEvent memorialEvent;

    @BeforeEach
    void setUp() {
        // Setup test data
        memorialAvroSchema = MemorialAvroSchema.newBuilder()
                .setMemorialId("memorial-123")
                .setWriterId("writer-123")
                .setContent("Test Memorial Content")
                .setCharacterId(123L)
                .build();

        memorialEvent = MemorialEvent.builder()
                .aggregateId("memorial-memorial-123")
                .aggregateType("MEMORIAL")
                .eventData(memorialAvroSchema)
                .build();
    }

    @Test
    @DisplayName("캐릭터 추모 중 상태 변경 테스트")
    void executeTest() {
        // Given
        when(eventMapper.memorialEvent(any(MemorialAvroSchema.class))).thenReturn(memorialEvent);
        doNothing().when(animeService).memorial();
        when(eventRepository.save(any(MemorialEvent.class))).thenReturn(memorialEvent);

        // When
        characterMemorializingUseCase.execute(memorialAvroSchema);

        // Then
        verify(animeService, times(1)).memorial();
        verify(eventMapper, times(1)).memorialEvent(memorialAvroSchema);
        verify(eventRepository, times(1)).save(memorialEvent);
    }
}