package windeath44.orchestration.application.service;

import org.apache.avro.specific.SpecificRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import windeath44.orchestration.domain.model.type.MemorialAction;
import windeath44.orchestration.domain.port.out.MemorialEventPublisher;

import java.util.Map;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MemorialServiceTest {

    @Mock
    private MemorialEventPublisher createPublisher;

    @Mock
    private MemorialEventPublisher compensatePublisher;

    @Mock
    private Map<MemorialAction, MemorialEventPublisher> memorialEventPublisher;

    @InjectMocks
    private MemorialService memorialService;

    @BeforeEach
    void setUp() {
        // Setup the mock publishers to return their respective actions
        when(createPublisher.getAction()).thenReturn(MemorialAction.CREATE);
        when(compensatePublisher.getAction()).thenReturn(MemorialAction.COMPENSATE);
    }

    @Test
    @DisplayName("CREATE 액션으로 execute 메서드 호출 시 올바른 publisher가 호출되는지 테스트")
    void executeWithCreateActionTest() {
        // Given
        SpecificRecord mockRecord = mock(SpecificRecord.class);
        when(memorialEventPublisher.get(MemorialAction.CREATE)).thenReturn(createPublisher);
        doNothing().when(createPublisher).publish(mockRecord);

        // When
        memorialService.execute(MemorialAction.CREATE, mockRecord);

        // Then
        verify(memorialEventPublisher, times(1)).get(MemorialAction.CREATE);
        verify(createPublisher, times(1)).publish(mockRecord);
    }

    @Test
    @DisplayName("COMPENSATE 액션으로 execute 메서드 호출 시 올바른 publisher가 호출되는지 테스트")
    void executeWithCompensateActionTest() {
        // Given
        SpecificRecord mockRecord = mock(SpecificRecord.class);
        when(memorialEventPublisher.get(MemorialAction.COMPENSATE)).thenReturn(compensatePublisher);
        doNothing().when(compensatePublisher).publish(mockRecord);

        // When
        memorialService.execute(MemorialAction.COMPENSATE, mockRecord);

        // Then
        verify(memorialEventPublisher, times(1)).get(MemorialAction.COMPENSATE);
        verify(compensatePublisher, times(1)).publish(mockRecord);
    }
}