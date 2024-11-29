package com.fc;

import com.fc.domain.NotificationType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class NotificationGetService {

    @Autowired
    private NotificationRepository repository;

    public Optional<Notification> getNotificationByTypeAndCommentId(NotificationType type, Long commentId) {
        return repository.findByTypeAndCommentId(type, commentId);
    }

    public Optional<Notification> getNotificationByTypeAndLikeId(NotificationType type, Long postId) {
        return repository.findByTypeAndPostId(type, postId);
    }

    public Optional<Notification> getNotificationByTypeAndUserIdAndFollowerId(NotificationType type, Long targetId, Long followerId) {

        log.info("LGW 뭐지? type :{}", type);
        log.info("LGW 뭐지? targetId :{}", targetId);
        log.info("LGW 뭐지? followerId :{}", followerId);

        Optional<Notification> byTypeAndUserIdAndFollowerId = repository.findByTypeAndUserIdAndFollowerId(type, targetId, followerId);

        if (byTypeAndUserIdAndFollowerId.isPresent()) {
            Notification notification = byTypeAndUserIdAndFollowerId.get();
            log.info("hello" + notification.toString());
        }

        return byTypeAndUserIdAndFollowerId;
    }
}
