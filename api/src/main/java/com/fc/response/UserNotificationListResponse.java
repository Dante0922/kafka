package com.fc.response;

import com.fc.controller.UserNotificationListController;
import com.fc.service.dto.GetUserNotificationsResult;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
import java.util.List;

@Getter
@AllArgsConstructor
@Schema(description = "유저 알림 목록 응답")
public class UserNotificationListResponse {
    private List<UserNotificationResponse> notifications;
    private Instant pivot;
    private boolean hasNext;

    public static UserNotificationListResponse of(GetUserNotificationsResult result) {

        List<UserNotificationResponse> notifications = result.getNotifications().stream()
            .map(UserNotificationResponse::of)
            .toList();

        Instant pivot = (result.isHasNext() && !notifications.isEmpty())
            ? notifications.getLast().getOccurredAt() : null;

        return new UserNotificationListResponse(
            notifications,
            pivot,
            result.isHasNext()
        );
    }
}
