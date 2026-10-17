package by.alexkrug.tracker.dto;


import by.alexkrug.tracker.utils.enums.TaskStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record TaskDto
        (
                @NotBlank(message = "description can`t be blank")
                String description,

                @Min(1)
                Long userId,

                LocalDate createdAt,

                TaskStatus status
        ) {

    public TaskDto (String description, Long userId) {
        this(description, userId, null, null);
    }

    public TaskDto (String description, TaskStatus taskStatus) {
        this(description, null, null, taskStatus);
    }
}
