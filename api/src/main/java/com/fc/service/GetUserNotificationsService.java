package com.fc.service;

import com.fc.domain.CommentNotification;
import com.fc.domain.FollowNotification;
import com.fc.domain.LikeNotification;
import com.fc.service.converter.CommentUserNotificationConverter;
import com.fc.service.converter.FollowUserNotificationConverter;
import com.fc.service.converter.LikeUserNotificationConverter;
import com.fc.service.dto.ConvertedNotification;
import com.fc.service.dto.GetUserNotificationByPivotResult;
import com.fc.service.dto.GetUserNotificationsResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
@Slf4j
public class GetUserNotificationsService {

    private final NotificationListService listService;
    private final CommentUserNotificationConverter commentConverter;
    private final LikeUserNotificationConverter likeConverter;
    private final FollowUserNotificationConverter followConverter;

    public GetUserNotificationsService(NotificationListService listService, CommentUserNotificationConverter commentConverter, LikeUserNotificationConverter likeConverter, FollowUserNotificationConverter followConverter) {
        this.listService = listService;
        this.commentConverter = commentConverter;
        this.likeConverter = likeConverter;
        this.followConverter = followConverter;
    }

    public GetUserNotificationsResult getUserNotificationsByPivot(Long userId, Instant pivot) {

        // 목록 조회
        GetUserNotificationByPivotResult result = listService.getUserNotificationByPivot(userId, pivot);

        log.info("lgw result.username: " + result.getNotifications().getFirst().getUserId());
        log.info("lgw result.username: " + result.getNotifications().getFirst().getId());

        // 목록을 순회하며 사용자 알림으로 변환
        List<ConvertedNotification> convertedNotifications = result.getNotifications().stream()
            .map(notification -> switch (notification.getType()) {
                    case COMMENT -> commentConverter.convert((CommentNotification) notification);
                    case LIKE -> likeConverter.convert((LikeNotification) notification);
                    case FOLLOW -> followConverter.convert((FollowNotification) notification);
                }
            ).toList();

        return new GetUserNotificationsResult(
            convertedNotifications,
            result.isHasNext()
        );



    }
}
