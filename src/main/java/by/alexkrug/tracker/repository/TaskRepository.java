package by.alexkrug.tracker.repository;

import by.alexkrug.tracker.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
