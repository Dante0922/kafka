package com.fc.event;

import com.fc.domain.FollowEventType;
import lombok.Data;

import java.time.Instant;

@Data
public class FollowEvent {
    private FollowEventType type;
    private Long userId;
    private Long targetUserId;
    private Instant createdAt;

}
