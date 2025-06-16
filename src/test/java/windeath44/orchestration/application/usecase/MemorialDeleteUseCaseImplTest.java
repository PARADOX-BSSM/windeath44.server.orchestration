package windeath44.orchestration.application.usecase;

import com.example.avro.MemorialAvroSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import windeath44.orchestration.application.service.MemorialService;
import windeath44.orchestration.domain.mapper.EventMapper;
import windeath44.orchestration.domain.model.MemorialEvent;
import windeath44.orchestration.domain.model.type.MemorialAction;
import windeath44.orchestration.domain.repository.EventRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MemorialDeleteUseCaseImplTest {

    @Mock
    private MemorialService memorialService;

    @Mock
    private EventMapper eventMapper;

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private MemorialDeleteUseCaseImpl memorialDeleteUseCase;

    private MemorialAvroSchema memorialAvroSchema;
    private MemorialEvent memorialEvent;

    @BeforeEach
    void setUp() {
        // Setup test data
        memorialAvroSchema = MemorialAvroSchema.newBuilder()
                .setMemorialId("memorial-123")
                .setWriterId("123")
                .setContent("Test Memorial Content")
                .setCharacterId(123L)
                .build();

        memorialEvent = MemorialEvent.builder()
                .aggregateId("memorial-application-123")
                .aggregateType("MEMORIAL")
                .eventData(memorialAvroSchema)
                .build();
    }

    @Test
    @DisplayName("추모관 삭제 요청 테스트 (보상 트랜잭션)")
    void executeTest() {
        // Given
        doNothing().when(memorialService).execute(eq(MemorialAction.COMPENSATE), any(MemorialAvroSchema.class));
        when(eventMapper.memorialCompensateEvent(any(MemorialAvroSchema.class))).thenReturn(memorialEvent);
        when(eventRepository.save(any(MemorialEvent.class))).thenReturn(memorialEvent);

        // When
        memorialDeleteUseCase.execute(memorialAvroSchema);

        // Then
        verify(memorialService, times(1)).execute(MemorialAction.COMPENSATE, memorialAvroSchema);
        verify(eventMapper, times(1)).memorialCompensateEvent(memorialAvroSchema);
        verify(eventRepository, times(1)).save(memorialEvent);
    }
}