package by.alexkrug.tracker.dto;

import java.time.LocalDate;

public record UserDto (
        Long userId,
        String name,
        String surname,
        LocalDate registeredAt
) {
}
