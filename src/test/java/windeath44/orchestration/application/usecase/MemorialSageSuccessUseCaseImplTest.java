package windeath44.orchestration.application.usecase;

import com.example.avro.MemorialApplicationAvroSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import windeath44.orchestration.domain.mapper.EventMapper;
import windeath44.orchestration.domain.model.MemorialApplicationEvent;
import windeath44.orchestration.domain.repository.EventRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MemorialSageSuccessUseCaseImplTest {

    @Mock
    private EventMapper eventMapper;

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private MemorialSageSuccessUseCaseImpl memorialSageSuccessUseCase;

    private MemorialApplicationAvroSchema memorialApplicationAvroSchema;
    private MemorialApplicationEvent memorialApplicationEvent;

    @BeforeEach
    void setUp() {
        // Setup test data
        memorialApplicationAvroSchema = MemorialApplicationAvroSchema.newBuilder()
                .setMemorialApplicationId("application-123")
                .setApplicantId("applicant-123")
                .setApproverId("approver-123")
                .setContent("Test Application Content")
                .setCharacterId(123L)
                .build();

        memorialApplicationEvent = MemorialApplicationEvent.builder()
                .aggregateId("memorial-application-application-123")
                .aggregateType("MEMORIAL_APPLICATION")
                .eventData(memorialApplicationAvroSchema)
                .build();
    }

    @Test
    @DisplayName("추모관 Saga 성공 처리 테스트")
    void executeTest() {
        // Given
        when(eventMapper.memorialApplicationEvent(any(MemorialApplicationAvroSchema.class))).thenReturn(memorialApplicationEvent);
        when(eventRepository.save(any(MemorialApplicationEvent.class))).thenReturn(memorialApplicationEvent);

        // When
        memorialSageSuccessUseCase.execute(memorialApplicationAvroSchema);

        // Then
        verify(eventMapper, times(1)).memorialApplicationEvent(memorialApplicationAvroSchema);
        verify(eventRepository, times(1)).save(memorialApplicationEvent);
    }
}