package com.fc.controller;

import com.fc.response.UserNotificationListResponse;
import com.fc.service.GetUserNotificationsService;
import com.fc.service.dto.GetUserNotificationsResult;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/v1/user-notification")
public class UserNotificationListController implements UserNotificationListControllerSpec {

    private final GetUserNotificationsService getUserNotificationsService;

    public UserNotificationListController(GetUserNotificationsService getUserNotificationsService) {
        this.getUserNotificationsService = getUserNotificationsService;
    }


    @GetMapping("/{userId}")
    public UserNotificationListResponse getNotifications(
        @PathVariable(value = "userId") Long userId,
        @RequestParam(value = "pivot", required = false) Instant pivot
    ) {
        GetUserNotificationsResult result = getUserNotificationsService.getUserNotificationsByPivot(userId, pivot);

        return UserNotificationListResponse.of(result);
    }

}
