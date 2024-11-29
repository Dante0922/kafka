package com.fc.domain;

import com.fc.domain.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.time.Instant;



@Getter
@Setter
@AllArgsConstructor
@Document("notifications")
public abstract class Notification {
    @Field(targetType = FieldType.STRING) //ObjectId('123') -> "123"으로 들어가지 않도록 명시
    private String id;
    private Long userId;
    private NotificationType type;
    private Instant occurredAt; // 알림 대상인 실제 이벤트가 발생한 시간
    private Instant createdAt;   // 알림이 생성된 시간
    private Instant lastUpdatedAt;
    private Instant deletedAt;   // 알림이 삭제될 시간 TTL
}
