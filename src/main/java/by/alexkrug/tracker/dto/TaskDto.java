package by.alexkrug.tracker.dto;


import by.alexkrug.tracker.utils.TaskStatus;

import java.time.LocalDate;

public record TaskDto
        (String description,
         Long userId,
         LocalDate createdAt,
         TaskStatus status) {

    public TaskDto (String description, Long userId) {
        this(description, userId, null, null);
    }

    public TaskDto (String description, TaskStatus taskStatus) {
        this(description, null, null, taskStatus);
    }
}
