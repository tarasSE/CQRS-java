package com.example.cqrs.todo_item.write;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class TodoWriteDTO {
    private String id;
    private String title;
    private String description;
    private Date createdAt;
    private Date updatedAt;
}
