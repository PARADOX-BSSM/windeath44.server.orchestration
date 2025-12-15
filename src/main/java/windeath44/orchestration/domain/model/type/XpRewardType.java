package windeath44.orchestration.domain.model.type;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@Getter
public enum XpRewardType {
    MEMORIAL_BOW("memorial-bow", "memorial_bow", 20L),
    MEMORIAL_COMMENT("memorial-comment", "memorial_comment", 30L);

    private final String requestKey;
    private final String source;
    private final long amount;

    XpRewardType(String requestKey, String source, long amount) {
        this.requestKey = requestKey;
        this.source = source;
        this.amount = amount;
    }

    public String buildRequestId() {
        return requestKey + "-" + UUID.randomUUID();
    }

    public String aggregateId(String userId) {
        return "xp-increase-" + requestKey + "-" + userId;
    }

    public boolean matchesRequestId(String requestId) {
        return requestId != null && requestId.startsWith(requestKey + "-");
    }

    public static Optional<XpRewardType> fromRequestId(String requestId) {
        return Arrays.stream(values())
                .filter(type -> type.matchesRequestId(requestId))
                .findFirst();
    }
}
