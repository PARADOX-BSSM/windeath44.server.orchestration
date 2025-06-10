package windeath44.orchestration.application.usecase;

import com.example.avro.MemorialAvroSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import windeath44.orchestration.application.service.MemorialService;
import windeath44.orchestration.domain.EventMapper;
import windeath44.orchestration.domain.model.MemorialEvent;
import windeath44.orchestration.domain.model.type.MemorialAction;
import windeath44.orchestration.domain.repository.EventRepository;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MemorialCreateUseCaseImplTest {

    @Mock
    private MemorialService memorialService;

    @Mock
    private EventMapper eventMapper;

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private MemorialCreateUseCaseImpl memorialCreateUseCase;

    private MemorialAvroSchema memorialAvroSchema;
    private MemorialEvent memorialEvent;
    private MemorialEvent memorialCompensateEvent;

    @BeforeEach
    void setUp() {
        // Create test data
        memorialAvroSchema = MemorialAvroSchema.newBuilder()
                .setMemorialId("test-id")
                .build();

        memorialEvent = MemorialEvent.builder()
                .aggregateId("memorial-test-id")
                .aggregateType("MEMORIAL")
                .build();

        memorialCompensateEvent = MemorialEvent.builder()
                .aggregateId("memorial-test-id")
                .aggregateType("MEMORIAL")
                .build();
    }

    @Test
    void execute_ShouldCallMemorialServiceAndSaveEvent() {
        // Arrange
        when(eventMapper.memorialEvent(memorialAvroSchema)).thenReturn(memorialEvent);

        // Act
        memorialCreateUseCase.execute(memorialAvroSchema);

        // Assert
        verify(memorialService, times(1)).execute(MemorialAction.CREATE, memorialAvroSchema);
        verify(eventMapper, times(1)).memorialEvent(memorialAvroSchema);
        verify(eventRepository, times(1)).save(memorialEvent);
    }

    @Test
    void compensate_ShouldCallMemorialServiceAndSaveCompensateEvent() {
        // Arrange
        when(eventMapper.memorialCompensateEvent(memorialAvroSchema)).thenReturn(memorialCompensateEvent);

        // Act
        memorialCreateUseCase.compensate(memorialAvroSchema);

        // Assert
        verify(memorialService, times(1)).execute(MemorialAction.COMPENSATE, memorialAvroSchema);
        verify(eventMapper, times(1)).memorialCompensateEvent(memorialAvroSchema);
        verify(eventRepository, times(1)).save(memorialCompensateEvent);
    }
}