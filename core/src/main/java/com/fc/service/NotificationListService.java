package com.fc.service;

import com.fc.domain.Notification;
import com.fc.repository.NotificationRepository;
import com.fc.service.dto.GetUserNotificationByPivotResult;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class NotificationListService {

    private final NotificationRepository repository;

    private static final int PAGE_SIZE = 20;

    public NotificationListService(NotificationRepository repository) {
        this.repository = repository;
    }
    // 목록 조회: Pivot 방식(occurredAt 기준) vs Paging 방식(page size, page number)
    // Paging은 순서 보장이 안 된다.

    public GetUserNotificationByPivotResult getUserNotificationByPivot(Long userId, Instant occurredAt) {

        Slice<Notification> result;

        if (occurredAt == null) {
            result = repository.findAllByUserIdOrderByOccurredAtDesc(userId, PageRequest.of(0, PAGE_SIZE));
        } else {
            result = repository.findAllByUserIdAndOccurredAtLessThanOrderByOccurredAtDesc(userId, occurredAt, PageRequest.of(0, PAGE_SIZE));
        }

        return new GetUserNotificationByPivotResult(
            result.toList(),
            result.hasNext()
        );

    }
}


