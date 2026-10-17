package by.alexkrug.tracker.service;


import by.alexkrug.tracker.dto.TaskDto;
import by.alexkrug.tracker.dto.UserDto;
import by.alexkrug.tracker.entity.Task;
import by.alexkrug.tracker.entity.User;
import by.alexkrug.tracker.repository.TaskRepository;
import by.alexkrug.tracker.repository.UserRepository;
import by.alexkrug.tracker.utils.enums.TaskStatus;
import by.alexkrug.tracker.utils.exceptions.TaskNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public List<TaskDto> getTasks() {
        return taskRepository.findAll()
                .stream()
                .map(this::taskToDto)
                .toList();
    }

    public TaskDto getTask(Long id) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (taskOptional.isEmpty()) {
            throw new TaskNotFoundException();
        }
        Task task = taskOptional.get();
        return taskToDto(task);
    }

    public void createTask(TaskDto taskDto) {
        Task task = toTask(taskDto);
        taskRepository.save(task);
    }

    public void updateTask(TaskDto taskDto, Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        boolean taskStatus;
        if (optionalTask.isEmpty()) {
            throw new TaskNotFoundException();
        }
        taskStatus = taskDto.status() == TaskStatus.PROCESS;
        Task task = optionalTask.get();
        task.setDescription(taskDto.description());
        task.setStatus(taskStatus);
    }

    public UserDto getUser(Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isEmpty()) {
            throw new TaskNotFoundException();
        }
        Task task = optionalTask.get();
        User user = task.getUser();
        return new UserDto(
                user.getUserId(),
                user.getName(),
                user.getSurname(),
                user.getRegisteredAt()
        );
    }

    public void deleteTask(Long id) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (taskOptional.isEmpty()) {
            throw new TaskNotFoundException();
        }
        Task task = taskOptional.get();
        taskRepository.delete(task);
    }



    private TaskDto taskToDto(Task task) {
        TaskStatus taskStatus;
        if (task.getStatus()) {
            taskStatus = TaskStatus.PROCESS;
        } else {
            taskStatus = TaskStatus.MADE;
        }
        return new TaskDto(
                task.getDescription(),
                task.getUser().getUserId(),
                task.getCreatedAt(),
                taskStatus
        );
    }

    private Task toTask(TaskDto taskDto) {
        Task task = new Task();
        Optional<User> optionalUser = userRepository.findById(taskDto.userId());
        if (optionalUser.isEmpty()) {
            throw new RuntimeException();
        }
        User user = optionalUser.get();

        task.setDescription(taskDto.description());
        task.setStatus(true);
        task.setCreatedAt(LocalDate.now());
        task.setUser(user);
        return task;
    }
}
