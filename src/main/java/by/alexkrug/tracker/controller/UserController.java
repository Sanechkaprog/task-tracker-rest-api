package by.alexkrug.tracker.controller;

import by.alexkrug.tracker.dto.UserDto;
import by.alexkrug.tracker.entity.Task;
import by.alexkrug.tracker.service.UserService;
import by.alexkrug.tracker.utils.UserNotFound;
import by.alexkrug.tracker.utils.exceptions.UserNotFoundException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private static final Log log = LogFactory.getLog(UserController.class);
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<UserDto> getAll() {
        return userService.getAll();
    }

    @GetMapping("/users/{id}")
    public UserDto getUser(@PathVariable("id") Long id) {
        return userService.getById(id);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<UserNotFound> handleException() {
        UserNotFound userNotFound= new UserNotFound();
        String message = "User is not found";
        LocalDateTime localDateTime = LocalDateTime.now();
        userNotFound.setMessage(message);
        userNotFound.setDate(localDateTime);
        log.fatal(message + " " + localDateTime);
        return new ResponseEntity<>(userNotFound, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/users")
    public ResponseEntity<HttpStatus> createUser(@RequestBody UserDto userDto) {
        userService.saveUser(userDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/users/{id}")
    public ResponseEntity<HttpStatus> updateUser(@RequestBody UserDto userDto, @PathVariable("id") Long id) {
        userService.updateUser(userDto, id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/users/{id}/tasks")
    public List<Task> getTasks(@PathVariable("id") Long id) {
        return userService.getTasks(id);
    }


}
