package com.fc.consumer;

import com.fc.event.LikeEvent;
import com.fc.task.LikeAddTask;
import com.fc.task.LikeRemoveTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

import static com.fc.domain.LikeEventType.ADD;
import static com.fc.domain.LikeEventType.REMOVE;

@Component
@Slf4j
public class LikeEventConsumer {

    @Autowired
    private LikeAddTask addTask;
    @Autowired
    private LikeRemoveTask removeTask;

    @Bean("like")
    public Consumer<LikeEvent> like() {
        return event -> {
            if (event.getType() == ADD) {
                addTask.processEvent(event);
            } else if (event.getType() == REMOVE) {
                removeTask.processEvent(event);
            }
        };
    }
}
