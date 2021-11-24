package com.example.cqrs.todo_item.read;

import com.example.cqrs.todo_item.write.TodoWriteDTO;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;

@Slf4j
@RequiredArgsConstructor
@Component
public class ReadSynchronizer implements Runnable {
    private final ExecutorCompletionService<TodoWriteDTO> readQueue;
    private final Map<String, TodoReadDTO> database;
    private boolean running = true;

    @PostConstruct
    private void postConstruct() {
        log.info("Constructed Synchronizer");
        new Thread(this).start();
    }

    @PreDestroy
    private void preDestroy() {
        running = false;
    }

    @SneakyThrows
    @Override
    public void run() {
        log.info("RUN Synchronizer");

        while (running) {
            // TODO: implement publisher/subscriber?
            var future = readQueue.take();
            log.info("Check for futures: {}", future.isDone());

            if (future.isDone()) {
                try {
                    var todo = future.get();
                    database.put(todo.getId(), TodoReadDTO.builder()
                            .title(todo.getTitle())
                            .description(todo.getDescription())
                            .build());

                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
