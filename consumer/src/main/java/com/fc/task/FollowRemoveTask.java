package com.fc.task;

import com.fc.NotificationGetService;
import com.fc.NotificationRemoveService;
import com.fc.domain.NotificationType;
import com.fc.event.FollowEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FollowRemoveTask {

    @Autowired
    NotificationGetService getService;
    @Autowired
    NotificationRemoveService removeService;

    public void processEvent(FollowEvent event) {

        getService.getNotificationByTypeAndUserIdAndFollowerId(NotificationType.FOLLOW, event.getTargetUserId(), event.getUserId())
            .ifPresent(
                notification -> removeService.deleteById(notification.getId())
            );
    }
}
