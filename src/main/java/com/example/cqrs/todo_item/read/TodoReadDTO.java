package com.example.cqrs.todo_item.read;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class TodoReadDTO {
    private String title;
    private String description;
}
