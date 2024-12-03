package com.fc.controller;

import com.fc.response.CheckNewNotificationResponse;
import com.fc.service.CheckNewNotificationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user-notifications")
public class CheckNewNotificationController implements CheckNewNotificationControllerSpec{

    public CheckNewNotificationController(CheckNewNotificationService checkNewNotificationService) {
        this.checkNewNotificationService = checkNewNotificationService;
    }

    private final CheckNewNotificationService checkNewNotificationService;

    @GetMapping("/{userId}/new")
    public CheckNewNotificationResponse checkNew(@PathVariable(value = "userId") Long userId) {

        boolean hasNew = checkNewNotificationService.checkNewNotification(userId);

        return new CheckNewNotificationResponse(hasNew);

    }
}
