package windeath44.orchestration.application.service;

import org.apache.avro.specific.SpecificRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import windeath44.orchestration.domain.model.type.MemorialApplicationAction;
import windeath44.orchestration.domain.port.out.MemorialApplicationEventPublisher;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MemorialApplicationServiceTest {

    @Mock
    private MemorialApplicationEventPublisher approvePublisher;

    @Mock
    private MemorialApplicationEventPublisher cancelPublisher;

    @Mock
    private Map<MemorialApplicationAction, MemorialApplicationEventPublisher> memorialApplicationEventPublisher;

    @InjectMocks
    private MemorialApplicationService memorialApplicationService;

    @BeforeEach
    void setUp() {
        // Setup the mock publishers to return their respective actions
        when(approvePublisher.getAction()).thenReturn(MemorialApplicationAction.APPROVE);
        when(cancelPublisher.getAction()).thenReturn(MemorialApplicationAction.CANCEL);
    }

    @Test
    @DisplayName("APPROVE 액션으로 execute 메서드 호출 시 올바른 publisher가 호출되는지 테스트")
    void executeWithApproveActionTest() {
        // Given
        SpecificRecord mockRecord = mock(SpecificRecord.class);
        when(memorialApplicationEventPublisher.get(MemorialApplicationAction.APPROVE)).thenReturn(approvePublisher);
        doNothing().when(approvePublisher).publish(mockRecord);

        // When
        memorialApplicationService.execute(MemorialApplicationAction.APPROVE, mockRecord);

        // Then
        verify(memorialApplicationEventPublisher, times(1)).get(MemorialApplicationAction.APPROVE);
        verify(approvePublisher, times(1)).publish(mockRecord);
    }

    @Test
    @DisplayName("CANCEL 액션으로 execute 메서드 호출 시 올바른 publisher가 호출되는지 테스트")
    void executeWithCancelActionTest() {
        // Given
        SpecificRecord mockRecord = mock(SpecificRecord.class);
        when(memorialApplicationEventPublisher.get(MemorialApplicationAction.CANCEL)).thenReturn(cancelPublisher);
        doNothing().when(cancelPublisher).publish(mockRecord);

        // When
        memorialApplicationService.execute(MemorialApplicationAction.CANCEL, mockRecord);

        // Then
        verify(memorialApplicationEventPublisher, times(1)).get(MemorialApplicationAction.CANCEL);
        verify(cancelPublisher, times(1)).publish(mockRecord);
    }
}