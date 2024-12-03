package com.fc.service.converter;

import com.fc.client.PostClient;
import com.fc.client.UserClient;
import com.fc.domain.CommentNotification;
import com.fc.domain.FollowNotification;
import com.fc.domain.Post;
import com.fc.domain.User;
import com.fc.service.dto.ConvertedCommentNotification;
import com.fc.service.dto.ConvertedFollowNotification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FollowUserNotificationConverter {

    private final UserClient userClient;


    public FollowUserNotificationConverter(UserClient userClient) {
        this.userClient = userClient;

    }

    public ConvertedFollowNotification convert(FollowNotification notification) {



        log.info("lgw followerId : " + notification.getFollowerId());

        User user = userClient.getUser(notification.getFollowerId());

        log.info("lgw username : ", user.getName());
        log.info("lgw userId : ", user.getId());
        boolean isFollowing = userClient.getIsFollowing(notification.getUserId(), notification.getFollowerId());

        return new ConvertedFollowNotification(
            notification.getId(),
            notification.getType(),
            notification.getOccurredAt(),
            notification.getLastUpdatedAt(),
            user.getName(),
            user.getProfileImageUrl(),
            isFollowing
        );
    }
}
