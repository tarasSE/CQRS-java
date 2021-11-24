package com.example.cqrs.todo_item.write;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/todo")
public class WriteController {
    private final TodoCommandHandler commandHandler;

    @PostMapping("/")
    public String postNotification(@RequestBody AddNewTodo command) {
        var id = commandHandler.handle(command);

        return id;
    }
}
