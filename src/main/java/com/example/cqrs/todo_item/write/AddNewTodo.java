package com.example.cqrs.todo_item.write;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
public class AddNewTodo implements ICommand {
    private String guid;
    private String title;
    private String description;

    @Override
    public String setId(String id) {
        return this.guid = id;
    }

    @Override
    public String getId() {
        return guid;
    }
}
