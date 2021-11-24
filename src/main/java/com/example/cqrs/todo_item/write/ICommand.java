package com.example.cqrs.todo_item.write;

public interface ICommand {
    String setId(String id);
    String getId();
}
