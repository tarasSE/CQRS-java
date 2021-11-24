package com.example.cqrs.todo_item.read;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
public class TodoDAO {
    private final Map<String, TodoReadDTO> database;

    public List<TodoReadDTO> findAll() {
        return database.values().stream().toList();
    }

    public TodoReadDTO findById(String id) {
        var todoReadDTO = database.get(id);
        if (isNull(todoReadDTO)) throw new RuntimeException("Todo is not found!");

        return todoReadDTO;
    }
}
