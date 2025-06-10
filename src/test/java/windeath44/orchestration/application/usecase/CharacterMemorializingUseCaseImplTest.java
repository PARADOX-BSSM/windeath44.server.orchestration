package windeath44.orchestration.application.usecase;

import com.example.avro.MemorialApplicationAvroSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import windeath44.orchestration.application.service.AnimeService;
import windeath44.orchestration.domain.EventMapper;
import windeath44.orchestration.domain.model.MemorialApplicationEvent;
import windeath44.orchestration.domain.repository.EventRepository;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CharacterMemorializingUseCaseImplTest {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private AnimeService animeService;

    @Mock
    private EventMapper eventMapper;

    @InjectMocks
    private CharacterMemorializingUseCaseImpl characterMemorializingUseCase;

    private MemorialApplicationAvroSchema memorialApplicationAvroSchema;
    private MemorialApplicationEvent memorialApplicationEvent;

    @BeforeEach
    void setUp() {
        // Create test data
        memorialApplicationAvroSchema = MemorialApplicationAvroSchema.newBuilder()
                .setMemorialApplicationId("test-id")
                .build();

        memorialApplicationEvent = MemorialApplicationEvent.builder()
                .aggregateId("memorial-application-test-id")
                .aggregateType("MEMORIAL_APPLICATION")
                .build();
    }

    @Test
    void execute_ShouldCallAnimeServiceAndSaveEvent() {
        // Arrange
        when(eventMapper.memorialApplicationEvent(memorialApplicationAvroSchema)).thenReturn(memorialApplicationEvent);

        // Act
        characterMemorializingUseCase.execute(memorialApplicationAvroSchema);

        // Assert
        verify(animeService, times(1)).memorial();
        verify(eventMapper, times(1)).memorialApplicationEvent(memorialApplicationAvroSchema);
        verify(eventRepository, times(1)).save(memorialApplicationEvent);
    }

    @Test
    void compensate_ShouldDoNothing() {
        // Act
        characterMemorializingUseCase.compensate(memorialApplicationAvroSchema);

        // Assert - verify no interactions with mocks since method is empty
        verifyNoInteractions(animeService, eventMapper, eventRepository);
    }
}