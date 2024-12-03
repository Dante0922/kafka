package com.fc.client;

import com.fc.domain.Comment;
import com.fc.domain.User;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UserClient {

    private final Map<Long, User> users = new HashMap<>();

    public UserClient() {
        users.put(1L, new User(1L, "user1","imageUrl1"));
        users.put(2L, new User(2L, "user2","imageUrl2"));
        users.put(3L, new User(3L, "user3","imageUrl3"));
    }

    public User getUser(Long id) {
        return users.get(id);
    }

    public Boolean getIsFollowing(Long followerId, Long followedId) {
        return true;
    }
}
