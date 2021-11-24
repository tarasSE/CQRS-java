package com.example.cqrs.todo_item.write;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.concurrent.*;

@Configuration
public class WriteModule {
    private static final Map<String, TodoWriteDTO> WRITE_DB = new ConcurrentHashMap<>();
//    private static final Executor executor = Executors.newFixedThreadPool(5);
//    private static final ExecutorCompletionService<ICommand> writeQueue = new ExecutorCompletionService<>(executor);

    @Bean
    Map<String, TodoWriteDTO> getWriteDb() {
        return WRITE_DB;
    }

    @Bean
    TodoRepositoryImpl todoRepository() {
        return new TodoRepositoryImpl(getWriteDb());
    }

//    @Provides
//    @WriteQueue
//    static ExecutorCompletionService<ICommand> writeQueue() {
//        return writeQueue;
//    }

    @Bean
    TodoCommandHandler todoCommandHandler(ExecutorCompletionService<TodoWriteDTO> readQueue) {
        return new TodoCommandHandler(todoRepository(), readQueue);
    }

}
