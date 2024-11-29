package com.fc.task;

import com.fc.*;
import com.fc.domain.NotificationType;
import com.fc.event.CommentEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CommentRemoveTask {

    @Autowired
    PostClient postClient;
    @Autowired
    NotificationGetService getService;
    @Autowired
    NotificationRemoveService removeService;

    public void processEvent(CommentEvent event) {

        Post post = postClient.getPost(event.getPostId());
        if (post.getUserId() == event.getUserId()) {
            return;
        }

        getService.getNotificationByTypeAndCommentId(NotificationType.COMMENT, event.getCommentId())
            .ifPresentOrElse(
                notification -> removeService.deleteById(notification.getId()),
                    () -> log.error("notification not found")
            );
    }
}
