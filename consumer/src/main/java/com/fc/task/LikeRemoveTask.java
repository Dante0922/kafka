package com.fc.task;

import com.fc.*;
import com.fc.domain.NotificationType;
import com.fc.event.LikeEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
@Slf4j
public class LikeRemoveTask {

    @Autowired
    NotificationGetService getService;
    @Autowired
    NotificationRemoveService removeService;
    @Autowired
    NotificationSaveService saveService;


    public void processEvent(LikeEvent event) {
        Optional<Notification> optionalNotification = getService.getNotificationByTypeAndLikeId(NotificationType.LIKE, event.getPostId());

        if (optionalNotification.isEmpty()) {
            log.error("No notification with postId: {}", event.getPostId());
            return;
        }

        // likers에서 event.userId를 제거
        // 1. likers가 비었으면 알림 삭제
        // 2. 남아있으면 업데이트

        LikeNotification notification = (LikeNotification) optionalNotification.get();

        removeLikerAndUpdateNotification(notification, event);
    }

    private void removeLikerAndUpdateNotification(LikeNotification notification, LikeEvent event) {
        notification.removeLiker(event.getUserId(), Instant.now());

        if (notification.getLikerIds().isEmpty()) {
            removeService.deleteById(notification.getId());
        } else {
            saveService.upsert(notification);
        }
    }

}
