package com.fc.service.dto;

import com.fc.domain.Notification;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class GetUserNotificationByPivotResult {
    private List<Notification> notifications;
    private boolean hasNext;
}
