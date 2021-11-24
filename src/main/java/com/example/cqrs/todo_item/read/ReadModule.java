package com.example.cqrs.todo_item.read;

import com.example.cqrs.todo_item.write.TodoWriteDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.concurrent.*;

@Configuration
public class ReadModule {
    private static final Map<String, TodoReadDTO> READ_DB = new ConcurrentHashMap<>();
    private static final Executor EXECUTOR = Executors.newFixedThreadPool(5);
    private static final ExecutorCompletionService<TodoWriteDTO> readQueue = new ExecutorCompletionService<>(EXECUTOR);

    @Bean
    Map<String, TodoReadDTO> readDb() {
        return READ_DB;
    }

    @Bean
    TodoDAO todoDAO() {
        return new TodoDAO(readDb());
    }

    @Bean
    public ExecutorCompletionService<TodoWriteDTO> readQueue() {
        return readQueue;
    }
}
