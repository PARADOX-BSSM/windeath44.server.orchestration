package windeath44.orchestration.domain.mapper;

import com.chatbot.events.ChatEvent;
import com.example.avro.MemorialApplicationAvroSchema;
import com.example.avro.MemorialAvroSchema;
import org.springframework.stereotype.Component;
import com.example.user.avro.RemainTokenDecreaseResponse;
import com.example.user.avro.RemainTokenIncreaseResponse;
import windeath44.orchestration.domain.model.ChatbotChatEvent;
import windeath44.orchestration.domain.model.MemorialApplicationEvent;
import windeath44.orchestration.domain.model.MemorialBowedEvent;
import windeath44.orchestration.domain.model.MemorialEvent;
import windeath44.orchestration.domain.model.RemainTokenDecreaseEvent;
import windeath44.orchestration.domain.model.RemainTokenIncreaseEvent;
import windeath44.orchestration.domain.model.type.EventType;
import windeath44.server.memorial.avro.MemorialBowedAvroSchema;

@Component
public class EventMapper {
  public MemorialEvent memorialEvent(MemorialAvroSchema memorialAvroSchema) {
    String aggregateId = "memorial-" + memorialAvroSchema.getMemorialId();
    String aggregateType = "MEMORIAL";
    EventType eventType = EventType.MEMORIAL_CREATED;

    return MemorialEvent.builder()
            .aggregateId(aggregateId)
            .aggregateType(aggregateType)
            .eventType(eventType)
            .eventData(memorialAvroSchema)
            .build();
  }

  public MemorialEvent memorialCompensateEvent(MemorialAvroSchema memorialAvroSchema) {
    String aggregateId = "memorial-" + memorialAvroSchema.getMemorialId();
    String aggregateType = "MEMORIAL";
    EventType eventType = EventType.MEMORIAL_DELETED;

    return MemorialEvent.builder()
            .aggregateId(aggregateId)
            .aggregateType(aggregateType)
            .eventType(eventType)
            .eventData(memorialAvroSchema)
            .build();
  }

  public MemorialApplicationEvent memorialApplicationEvent(MemorialApplicationAvroSchema memorialApplicationAvroSchema) {
    String aggregateId = "memorial-application-" + memorialApplicationAvroSchema.getMemorialApplicationId();
    String aggregateType = "MEMORIAL_APPLICATION";
    EventType eventType = EventType.MEMORIAL_APPLICATION_APPROVED_REQUEST;

    return MemorialApplicationEvent.builder()
            .aggregateId(aggregateId)
            .aggregateType(aggregateType)
            .eventType(eventType)
            .eventData(memorialApplicationAvroSchema)
            .build();
  }

  public MemorialApplicationEvent memorialApplicationCompensateEvent(MemorialApplicationAvroSchema memorialApplicationAvroSchema) {
    String aggregateId = "memorial-application-" + memorialApplicationAvroSchema.getMemorialApplicationId();
    String aggregateType = "MEMORIAL_APPLICATION";
    EventType eventType = EventType.MEMORIAL_APPLICATION_CANCELLED;

    return MemorialApplicationEvent.builder()
            .aggregateId(aggregateId)
            .aggregateType(aggregateType)
            .eventType(eventType)
            .eventData(memorialApplicationAvroSchema)
            .build();
  }

  public ChatbotChatEvent chatEvent(ChatEvent chatEvent) {
    String aggregateId = "chatbot-chat-" + chatEvent.getChatbotId() + "-" + chatEvent.getSessionId();
    String aggregateType = "CHATBOT_CHAT";
    EventType eventType = EventType.CHATBOT_CHAT_REQUEST;

    return ChatbotChatEvent.builder()
            .aggregateId(aggregateId)
            .aggregateType(aggregateType)
            .eventType(eventType)
            .eventData(chatEvent)
            .build();
  }

  public ChatbotChatEvent chatEventSuccessResponse(ChatEvent chatEvent) {
    String aggregateId = "chatbot-chat-" + chatEvent.getChatbotId() + "-" + chatEvent.getSessionId();
    String aggregateType = "CHATBOT_CHAT";
    EventType eventType = EventType.REMAIN_TOKEN_DECREASE_RESPONSE;

    return ChatbotChatEvent.builder()
            .aggregateId(aggregateId)
            .aggregateType(aggregateType)
            .eventType(eventType)
            .eventData(chatEvent)
            .build();
  }

  public ChatbotChatEvent chatEventFailResponse(ChatEvent chatEvent) {
    String aggregateId = "chatbot-chat-" + chatEvent.getChatbotId() + "-" + chatEvent.getSessionId();
    String aggregateType = "CHATBOT_CHAT";
    EventType eventType = EventType.REMAIN_TOKEN_DECREASE_FAIL_RESPONSE;

    return ChatbotChatEvent.builder()
            .aggregateId(aggregateId)
            .aggregateType(aggregateType)
            .eventType(eventType)
            .eventData(chatEvent)
            .build();
  }

  public RemainTokenDecreaseEvent remainTokenDecreaseSuccessResponse(RemainTokenDecreaseResponse remainTokenDecreaseResponse) {
    String aggregateId = "remain-token-decrease-" + remainTokenDecreaseResponse.getUserId();
    String aggregateType = "REMAIN_TOKEN_DECREASE";
    EventType eventType = EventType.REMAIN_TOKEN_DECREASE_RESPONSE;

    return RemainTokenDecreaseEvent.builder()
            .aggregateId(aggregateId)
            .aggregateType(aggregateType)
            .eventType(eventType)
            .eventData(remainTokenDecreaseResponse)
            .build();
  }

  public RemainTokenDecreaseEvent remainTokenDecreaseFailResponse(RemainTokenDecreaseResponse remainTokenDecreaseResponse) {
    String aggregateId = "remain-token-decrease-" + remainTokenDecreaseResponse.getUserId();
    String aggregateType = "REMAIN_TOKEN_DECREASE";
    EventType eventType = EventType.REMAIN_TOKEN_DECREASE_FAIL_RESPONSE;

    return RemainTokenDecreaseEvent.builder()
            .aggregateId(aggregateId)
            .aggregateType(aggregateType)
            .eventType(eventType)
            .eventData(remainTokenDecreaseResponse)
            .build();
  }

  public MemorialBowedEvent memorialBowedEvent(MemorialBowedAvroSchema memorialBowedAvroSchema) {
    String aggregateId = "memorial-bowed-" + memorialBowedAvroSchema.getMemorialId();
    String aggregateType = "MEMORIAL_BOWED";
    EventType eventType = EventType.MEMORIAL_BOWED;

    return MemorialBowedEvent.builder()
            .aggregateId(aggregateId)
            .aggregateType(aggregateType)
            .eventType(eventType)
            .eventData(memorialBowedAvroSchema)
            .build();
  }

  public RemainTokenIncreaseEvent remainTokenIncreaseResponse(RemainTokenIncreaseResponse remainTokenIncreaseResponse) {
    String aggregateId = "remain-token-increase-" + remainTokenIncreaseResponse.getUserId();
    String aggregateType = "REMAIN_TOKEN_INCREASE";
    EventType eventType = EventType.REMAIN_TOKEN_INCREASE_RESPONSE;

    return RemainTokenIncreaseEvent.builder()
            .aggregateId(aggregateId)
            .aggregateType(aggregateType)
            .eventType(eventType)
            .eventData(remainTokenIncreaseResponse)
            .build();
  }

}