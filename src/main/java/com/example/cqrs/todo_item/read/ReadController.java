package com.example.cqrs.todo_item.read;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/todo")
public class ReadController {
    private final TodoDAO todoDAO;

    @GetMapping
    public List<TodoReadDTO> findAll() {
        var todoList = todoDAO.findAll();
        return todoList;
    }

    @GetMapping("/{id}")
    public TodoReadDTO getNotification(@PathVariable String id) {
        var todo = todoDAO.findById(id);
        return todo;
    }
}
