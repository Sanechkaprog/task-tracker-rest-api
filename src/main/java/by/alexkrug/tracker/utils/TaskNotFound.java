package by.alexkrug.tracker.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class TaskNotFound {
    private String message;
    private LocalDateTime dateTime;
}
