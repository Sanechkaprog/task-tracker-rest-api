package by.alexkrug.tracker.controller;


import by.alexkrug.tracker.dto.TaskDto;
import by.alexkrug.tracker.dto.UserDto;
import by.alexkrug.tracker.service.TaskService;
import by.alexkrug.tracker.utils.TaskNotFound;
import by.alexkrug.tracker.utils.exceptions.TaskNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public List<TaskDto> getTasks() {
        return taskService.getTasks();
    }

    @GetMapping("/tasks/{id}")
    public TaskDto getTask(@PathVariable("id") Long id) {
        return taskService.getTask(id);
    }

    @PostMapping("/tasks")
    public ResponseEntity<HttpStatus> createTask(@Valid @RequestBody TaskDto taskDto) {
        taskService.createTask(taskDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/tasks/{id}")
    public ResponseEntity<HttpStatus> updateTask(@Valid @RequestBody TaskDto taskDto, @PathVariable("id") Long id) {
        taskService.updateTask(taskDto, id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/tasks/{id}/user")
    public UserDto getUser(@PathVariable("id") Long id) {
        return taskService.getUser(id);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<HttpStatus> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<TaskNotFound> handle(TaskNotFoundException e) {
        TaskNotFound taskNotFound = new TaskNotFound();
        taskNotFound.setMessage("Task is not found");
        taskNotFound.setDateTime(LocalDateTime.now());
        return new ResponseEntity<>(taskNotFound, HttpStatus.NOT_FOUND);
    }
}
