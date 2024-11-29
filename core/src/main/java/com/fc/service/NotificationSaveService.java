package com.fc.service;

import com.fc.domain.Notification;
import com.fc.repository.NotificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NotificationSaveService {

    @Autowired
    private NotificationRepository repository;

    public void insert(Notification notification) {
        Notification result = repository.insert(notification);
        log.info("inserted result: {}", result);
    }

    public void upsert(Notification notification) {
        Notification result = repository.save(notification);
        log.info("upsert result: {}", result);
    }
}
