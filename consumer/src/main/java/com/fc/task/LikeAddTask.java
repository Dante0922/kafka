package com.fc.task;


import com.fc.*;
import com.fc.domain.NotificationType;
import com.fc.event.LikeEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class LikeAddTask {

    @Autowired
    PostClient postClient;
    @Autowired
    NotificationGetService notificationGetService;
    @Autowired
    NotificationSaveService notificationSaveService;

    public void processEvent(LikeEvent event) {

        Post post = postClient.getPost(event.getPostId());
        if (post == null) {
            log.error("Post is null with postId: {}", post.getId());
        }

        if (post.getUserId().equals(event.getUserId())) {
            return;
        }

        // likeNotification 1. 신규 생성 2. 업데이트
        Notification notification = createOrUpdateNotification(post, event);

        // 저장
        notificationSaveService.upsert(notification);

    }

    private Notification createOrUpdateNotification(Post post, LikeEvent event) {
        Optional<Notification> optionalNotification = notificationGetService.getNotificationByTypeAndLikeId(NotificationType.LIKE, post.getId());

        Instant now = Instant.now();
        Instant retention = Instant.now().plus(90, ChronoUnit.DAYS);

        if (optionalNotification.isPresent()) {
            // 업데이트
            return updateNotification((LikeNotification) optionalNotification.get(), event, now, retention);
        } else {
            // 신규 생성
            return createNotification(post, event, now
                , retention);
        }
    }


    private Notification createNotification(Post post, LikeEvent event, Instant now, Instant retention) {

        return new LikeNotification(
            NotificationIdGenerator.generate(),
            post.getUserId(),
            NotificationType.LIKE,
            event.getCreatedAt(),
            now,
            now,
            retention,
            post.getId(),
            List.of(event.getUserId())
        );
    }

    private Notification updateNotification(LikeNotification notification, LikeEvent event, Instant now, Instant retention) {
        notification.addLiker(
            event.getUserId(),
            event.getCreatedAt(),
            now,
            retention
        );
        return notification;
    }

}
