package com.fc;

import com.fc.Notification;
import com.fc.domain.NotificationType;
import lombok.Getter;
import org.springframework.data.annotation.TypeAlias;

import java.time.Instant;
import java.util.List;

@TypeAlias("LikeNotification")
@Getter
public class LikeNotification extends Notification {
    private final Long postId;
    private final List<Long> likerIds;


    public LikeNotification(String id, Long userId, NotificationType type, Instant occurredAt, Instant createdAt, Instant lastUpdatedAt, Instant deletedAt, Long postId, List<Long> likerIds) {
        super(id, userId, type, occurredAt, createdAt, lastUpdatedAt, deletedAt);
        this.postId = postId;
        this.likerIds = likerIds;
    }

    public void addLiker(Long likerId, Instant occurredAt, Instant now, Instant retention) {
        likerIds.add(
            likerId);
        setOccurredAt(occurredAt);
        setLastUpdatedAt(now);
        setDeletedAt(retention);
    }

    public void removeLiker(Long likerId, Instant now) {
        likerIds.remove(likerId);
        setLastUpdatedAt(now);
    }

}
