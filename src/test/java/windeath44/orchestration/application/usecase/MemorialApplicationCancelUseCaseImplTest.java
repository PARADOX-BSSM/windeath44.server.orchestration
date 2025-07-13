package windeath44.orchestration.application.usecase;

import com.example.avro.MemorialAvroSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import windeath44.orchestration.application.service.MemorialApplicationService;
import windeath44.orchestration.domain.mapper.EventMapper;
import windeath44.orchestration.domain.model.MemorialEvent;
import windeath44.orchestration.domain.model.type.MemorialApplicationAction;
import windeath44.orchestration.domain.repository.EventRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MemorialApplicationCancelUseCaseImplTest {

    @Mock
    private EventMapper eventMapper;

    @Mock
    private EventRepository eventRepository;

    @Mock
    private MemorialApplicationService memorialApplicationService;

    @InjectMocks
    private MemorialApplicationCancelUseCaseImpl memorialApplicationCancelUseCase;

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
    @DisplayName("추모관 신청 취소 테스트")
    void executeTest() {
        // Given
        doNothing().when(memorialApplicationService).execute(eq(MemorialApplicationAction.CANCEL), any(MemorialAvroSchema.class));
        when(eventMapper.memorialCompensateEvent(any(MemorialAvroSchema.class))).thenReturn(memorialEvent);
        when(eventRepository.save(any(MemorialEvent.class))).thenReturn(memorialEvent);

        // When
        memorialApplicationCancelUseCase.execute(memorialAvroSchema);

        // Then
        verify(memorialApplicationService, times(1)).execute(MemorialApplicationAction.CANCEL, memorialAvroSchema);
        verify(eventMapper, times(1)).memorialCompensateEvent(memorialAvroSchema);
        verify(eventRepository, times(1)).save(memorialEvent);
    }
}