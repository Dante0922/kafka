package com.fc.event;

import com.fc.domain.LikeEventType;
import lombok.Data;

import java.time.Instant;

@Data
public class LikeEvent {
    private LikeEventType type;
    private Long postId;
    private Long userId;    // 좋아요한 유저
    private Instant createdAt;

}
