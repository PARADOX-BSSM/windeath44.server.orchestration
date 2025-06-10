package windeath44.orchestration.application.service;

import com.example.avro.MemorialAvroSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import windeath44.orchestration.domain.model.type.MemorialAction;
import windeath44.orchestration.domain.port.out.MemorialEventPublisher;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MemorialServiceTest {

    @Mock
    private MemorialEventPublisher createPublisher;

    @Mock
    private MemorialEventPublisher compensatePublisher;

    @InjectMocks
    private MemorialService memorialService;

    private MemorialAvroSchema memorialAvroSchema;
    private Map<MemorialAction, MemorialEventPublisher> publisherMap;

    @BeforeEach
    void setUp() {
        // Create test data
        memorialAvroSchema = MemorialAvroSchema.newBuilder()
                .setMemorialId("test-id")
                .build();

        // Set up the publisher map
        publisherMap = new HashMap<>();
        publisherMap.put(MemorialAction.CREATE, createPublisher);
        publisherMap.put(MemorialAction.COMPENSATE, compensatePublisher);

        // Inject the map into the service
        memorialService = new MemorialService(publisherMap);
    }

    @Test
    void execute_WithCreateAction_ShouldCallCreatePublisher() {
        // Act
        memorialService.execute(MemorialAction.CREATE, memorialAvroSchema);

        // Assert
        verify(createPublisher, times(1)).publish(memorialAvroSchema);
        verifyNoInteractions(compensatePublisher);
    }

    @Test
    void execute_WithCompensateAction_ShouldCallCompensatePublisher() {
        // Act
        memorialService.execute(MemorialAction.COMPENSATE, memorialAvroSchema);

        // Assert
        verify(compensatePublisher, times(1)).publish(memorialAvroSchema);
        verifyNoInteractions(createPublisher);
    }
}