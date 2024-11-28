package com.fc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootApplication
@SpringBootTest
class NotificationRepositoryMemoryImplTest {

    @Autowired
    private NotificationRepository sut;

    private final Instant now = Instant.now();
    private final Instant deletedAt = now.plus(90, ChronoUnit.DAYS);

    @Test
    void test_save() {
        // 알림 객체 생성
        // 저장
        // 조회

        Notification notification = new Notification("1", 2L, NotificationType.LIKE, now, deletedAt);

        sut.save(notification);

        Optional<Notification> noti = sut.findById("1");
        assertTrue(noti.isPresent());
    }

    @Test
    void test_find_by_id() {
        Notification notification = new Notification("2", 2L, NotificationType.LIKE, now, deletedAt);

        sut.save(notification);

        Optional<Notification> optionalNotification = sut.findById("2");
        Notification noti = optionalNotification.orElseThrow();
        assertEquals(noti.id, "2");
        assertEquals(noti.userId, 2L);
        assertEquals(notification.createdAt, now);
        assertEquals(notification.deletedAt, deletedAt);
    }

    @Test
    void test_delete_by_id() {
        Notification notification = new Notification("3", 3L, NotificationType.LIKE, now, deletedAt);
        sut.save(notification);

        sut.deleteById("3");

        Optional<Notification> optionalNotification = sut.findById("3");
        assertFalse(optionalNotification.isPresent());

    }
}