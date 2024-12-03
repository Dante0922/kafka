package com.fc.service;

import com.fc.domain.Notification;
import com.fc.repository.NotificationRepository;
import com.fc.domain.NotificationType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.time.Instant;
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
        return repository.findByTypeAndUserIdAndFollowerId(type, targetId, followerId);
    }

    public Instant getLatestUpdatedAt(Long userId) {
        Optional<Notification> notification = repository.findFirstByUserIdOrderByLastUpdatedAtDesc(userId);

        if (notification.isEmpty()) {
            return null;
        }
        return notification.get().getLastUpdatedAt();
    }
}
