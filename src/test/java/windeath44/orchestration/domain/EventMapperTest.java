package windeath44.orchestration.domain;

import com.example.avro.MemorialApplicationAvroSchema;
import com.example.avro.MemorialAvroSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import windeath44.orchestration.domain.model.MemorialApplicationEvent;
import windeath44.orchestration.domain.model.MemorialEvent;
import windeath44.orchestration.domain.model.type.EventType;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EventMapperTest {

    private EventMapper eventMapper;
    private MemorialAvroSchema memorialAvroSchema;
    private MemorialApplicationAvroSchema memorialApplicationAvroSchema;

    @BeforeEach
    void setUp() {
        eventMapper = new EventMapper();

        // Create test data
        memorialAvroSchema = MemorialAvroSchema.newBuilder()
                .setMemorialId("test-memorial-id")
                .build();

        memorialApplicationAvroSchema = MemorialApplicationAvroSchema.newBuilder()
                .setMemorialApplicationId("test-application-id")
                .build();
    }

    @Test
    void memorialEvent_ShouldMapCorrectly() throws Exception {
        // Act
        MemorialEvent result = eventMapper.memorialEvent(memorialAvroSchema);

        // Assert
        assertNotNull(result);
        assertEquals("memorial-test-memorial-id", getFieldValue(result, "aggregateId"));
        assertEquals("MEMORIAL", getFieldValue(result, "aggregateType"));
        assertEquals(EventType.MEMORIAL_CREATED, getFieldValue(result, "eventType"));
        assertEquals(memorialAvroSchema, getFieldValue(result, "eventData"));
    }

    @Test
    void memorialCompensate_ShouldMapCorrectly() throws Exception {
        // Act
        MemorialEvent result = eventMapper.memorialCompensateEvent(memorialAvroSchema);

        // Assert
        assertNotNull(result);
        assertEquals("memorial-test-memorial-id", getFieldValue(result, "aggregateId"));
        assertEquals("MEMORIAL", getFieldValue(result, "aggregateType"));
        assertEquals(EventType.MEMORIAL_DELETED, getFieldValue(result, "eventType"));
        assertEquals(memorialAvroSchema, getFieldValue(result, "eventData"));
    }

    @Test
    void memorialApplication_ShouldMapCorrectly() throws Exception {
        // Act
        MemorialApplicationEvent result = eventMapper.memorialApplicationEvent(memorialApplicationAvroSchema);

        // Assert
        assertNotNull(result);
        assertEquals("memorial-application-test-application-id", getFieldValue(result, "aggregateId"));
        assertEquals("MEMORIAL_APPLICATION", getFieldValue(result, "aggregateType"));
        assertEquals(EventType.MEMORIAL_APPLICATION_APPROVED, getFieldValue(result, "eventType"));
        assertEquals(memorialApplicationAvroSchema, getFieldValue(result, "eventData"));
    }

    // Helper method to access private fields using reflection
    private <T> T getFieldValue(Object obj, String fieldName) throws Exception {
        Field field = obj.getClass().getSuperclass().getDeclaredField(fieldName);
        field.setAccessible(true);
        @SuppressWarnings("unchecked")
        T value = (T) field.get(obj);
        return value;
    }
}
