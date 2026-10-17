package by.alexkrug.tracker.controller;


import by.alexkrug.tracker.dto.TaskDto;
import by.alexkrug.tracker.dto.UserDto;
import by.alexkrug.tracker.entity.User;
import by.alexkrug.tracker.service.TaskService;
import by.alexkrug.tracker.utils.TaskNotFound;
import by.alexkrug.tracker.utils.exceptions.TaskNotFoundException;
import by.alexkrug.tracker.utils.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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
    public ResponseEntity<HttpStatus> createTask(@RequestBody TaskDto taskDto) {
        taskService.createTask(taskDto);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PatchMapping("/tasks/{id}")
    public ResponseEntity<HttpStatus> updateTask(@RequestBody TaskDto taskDto, @PathVariable("id") Long id) {
        taskService.updateTask(taskDto, id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/tasks/{id}/user")
    public UserDto getUser(@PathVariable("id") Long id) {
        return taskService.getUser(id);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<HttpStatus> deleteTask(@PathVariable Long id) {
        taskService.deleteUser(id);
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
