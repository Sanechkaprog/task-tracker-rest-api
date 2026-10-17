package by.alexkrug.tracker.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class UserNotFound {
    private String message;
    private LocalDateTime date;
}
