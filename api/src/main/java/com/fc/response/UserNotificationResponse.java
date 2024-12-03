package com.fc.response;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fc.domain.NotificationType;
import com.fc.service.dto.ConvertedNotification;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
@Schema(description = "유저 알림 응답")
@JsonSubTypes({@JsonSubTypes.Type(value = CommentUserNotificationResponse.class),
    @JsonSubTypes.Type(value = FollowUserNotificationResponse.class),
    @JsonSubTypes.Type(value = LikeUserNotificationResponse.class)})
public class UserNotificationResponse {
    @Schema(description = "알림 ID")
    private String id;
    @Schema(description = "알림 타입")
    private NotificationType type;
    @Schema(description = "알림 이벤트 발생 시간")
    private Instant occurredAt;


    public static UserNotificationResponse of(ConvertedNotification notification) {
        switch (notification.getType()) {
            case COMMENT -> {
                return CommentUserNotificationResponse.of(notification);
            }
            case LIKE -> {
                return LikeUserNotificationResponse.of(notification);
            }
            case FOLLOW -> {
                return FollowUserNotificationResponse.of(notification);
            }
            default -> throw new IllegalArgumentException("Unsupported notifcation type: " + notification.getType());
        }

    }
}