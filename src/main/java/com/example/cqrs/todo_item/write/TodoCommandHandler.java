package com.example.cqrs.todo_item.write;

import com.example.cqrs.utils.KeyGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;

import java.util.concurrent.ExecutorCompletionService;

@Slf4j
@RequiredArgsConstructor
public class TodoCommandHandler {
    private final TodoRepositoryImpl todoRepository;
    private final ExecutorCompletionService<TodoWriteDTO> readQueue;


    private TodoWriteDTO handle(AddNewTodo addNewTodo) {
        var todo = TodoWriteDTO.builder()
                .id(KeyGenerator.next())
                .title(addNewTodo.getTitle())
                .description(addNewTodo.getDescription())
                .createdAt(DateTime.now().toDate())
                .updatedAt(DateTime.now().toDate())
                .build();

        return todoRepository.create(todo);
    }

    public String handle(ICommand command) {
        var id = command.setId(KeyGenerator.next());
        log.info("Command: {}", command);

        readQueue.submit(() -> {
            if (command instanceof AddNewTodo addNewTodo) {
                var todoWriteDTO = handle(addNewTodo);
                log.info("Command executed: {}", todoWriteDTO);
                return todoWriteDTO;
            }

            return null;
        });
        return id;
    }
}
