package com.fc.api;

import com.fc.event.CommentEvent;
import com.fc.event.LikeEvent;
import com.fc.task.CommentRemoveTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Consumer;

@RestController
public class EventConsumerTestController implements EventConsumerTestControllerSpec {

    @Autowired
    private Consumer<CommentEvent> comment;

    @Autowired
    private Consumer<LikeEvent> like;


    @Autowired
    CommentRemoveTask commentRemoveTask;

    @PostMapping("/test/comment")
    public void comment(@RequestBody CommentEvent event) {
        comment.accept(event);
    }

    @PostMapping("/test/like")
    public void like(@RequestBody LikeEvent event) {
        like.accept(event);
    }
}
