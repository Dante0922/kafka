package com.fc.service.dto;

import com.fc.domain.NotificationType;
import lombok.Getter;

import java.time.Instant;

@Getter
public class ConvertedCommentNotification extends ConvertedNotification {
    private final String userName;
    private final String userProfileImageUrl;
    private final String content;
    private final String postImageUrl;


    public ConvertedCommentNotification(String id, NotificationType type, Instant occurredAt, Instant lastUpdatedAt, String userName, String userProfileImageUrl, String content, String postImageUrl) {
        super(id, type, occurredAt, lastUpdatedAt);
        this.userName = userName;
        this.userProfileImageUrl = userProfileImageUrl;
        this.content = content;
        this.postImageUrl = postImageUrl;
    }
}