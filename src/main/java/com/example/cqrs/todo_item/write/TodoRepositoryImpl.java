package com.example.cqrs.todo_item.write;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class TodoRepositoryImpl {
    private final Map<String, TodoWriteDTO> database;

    public TodoWriteDTO create(TodoWriteDTO todoWriteDTO) {
        if (database.containsKey(todoWriteDTO.getId())) {
            throw new RuntimeException("The key is already exists!");
        }

        log.info("Create TODO: {}", todoWriteDTO);
        database.put(todoWriteDTO.getId(), todoWriteDTO);

        return database.get(todoWriteDTO.getId());
    }
}
