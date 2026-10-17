package by.alexkrug.tracker.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record UserDto (
        Long userId,

        @NotBlank(message = "name can`t be empty")
        String name,

        @NotBlank(message = "surname can`t be empty")
        String surname,

        LocalDate registeredAt
) {
    public UserDto(String name, String surname) {
        this(null, name, surname, null);
    }
}
