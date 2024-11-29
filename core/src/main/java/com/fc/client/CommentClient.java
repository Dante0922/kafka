package com.fc.client;

import com.fc.domain.Comment;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Component
public class CommentClient {

    // MSA 환경으로 가정하고 구현. POST 서버에서 가져온다..

    private final Map<Long, Comment> comments = new HashMap<>();

    public CommentClient() {
        comments.put(1L, new Comment(1L, 111L,"Hello1", Instant.now()));
        comments.put(2L, new Comment(2L, 222L,"Hello2", Instant.now()));
        comments.put(3L, new Comment(3L, 333L,"Hello3", Instant.now()));
    }

    public Comment getClient(Long id) {
        return comments.get(id);
    }

}
