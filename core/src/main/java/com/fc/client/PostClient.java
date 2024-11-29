package com.fc.client;

import com.fc.domain.Post;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PostClient {

    // MSA 환경으로 가정하고 구현. POST 서버에서 가져온다..

    private final Map<Long, Post> posts = new HashMap<>();

    public PostClient() {
        posts.put(1L, new Post(1L, 111L, "imageURL111", "content1"));
        posts.put(2L, new Post(2L, 222L, "imageURL222", "content2"));
    }

    public Post getPost(Long id) {
        return posts.get(id);
    }

}
