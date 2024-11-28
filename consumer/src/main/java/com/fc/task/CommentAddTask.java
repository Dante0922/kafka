package com.fc.task;

import com.fc.*;
import com.fc.domain.NotificationType;
import com.fc.event.CommentEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
public class CommentAddTask {

    @Autowired
    PostClient postClient;
    @Autowired
    CommentClient commentClient;
    @Autowired
    NotificationSaveService notificationSaveService;

    public void processEvent(CommentEvent event) {
        // 내가 작성한 댓글인 경우 무시

        Post post = postClient.getPost(event.getPostId());
        if (post.getUserId() == event.getUserId()) {
            return;
        }

        Comment comment = commentClient.getClient(event.getCommentId());

        // 알림생성
        Notification notification = createNotification(post, comment);

        // 저장
        notificationSaveService.insert(notification);

    }

    private Notification createNotification(Post post, Comment comment) {

        Instant now = Instant.now();

        return new CommentNotification(
            NotificationIdGenerator.generate(),
            post.getUserId(),
            NotificationType.COMMENT,
            comment.getCreatedAt(),
            now,
            now,
            now.plus(90, ChronoUnit.DAYS),
            post.getId(),
            comment.getUserId(),
            comment.getContent(),
            comment.getId());
    }
}
