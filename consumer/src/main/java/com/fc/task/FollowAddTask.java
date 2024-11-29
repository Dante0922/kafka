package com.fc.task;

import com.fc.domain.FollowNotification;
import com.fc.utils.NotificationIdGenerator;
import com.fc.service.NotificationSaveService;
import com.fc.domain.NotificationType;
import com.fc.event.FollowEvent;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
public class FollowAddTask {

    private final NotificationSaveService saveService;

    public FollowAddTask(NotificationSaveService saveService) {
        this.saveService = saveService;
    }

    public void processEvent(FollowEvent event) {

        FollowNotification followNotification = createFollowNotification(event);

        saveService.insert(followNotification);

    }

    @NotNull
    private static FollowNotification createFollowNotification(FollowEvent event) {
        Instant now = Instant.now();
        Instant retention = Instant.now().plus(90, ChronoUnit.DAYS);

        FollowNotification followNotification = new FollowNotification(
            NotificationIdGenerator.generate(),
            event.getTargetUserId(),
            NotificationType.FOLLOW,
            event.getCreatedAt(),
            now,
            now,
            retention,
            event.getUserId()
        );
        return followNotification;
    }
}
