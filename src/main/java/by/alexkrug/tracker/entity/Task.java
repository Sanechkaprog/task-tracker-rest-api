package by.alexkrug.tracker.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="tasks")
@ToString
public class Task {
    @Id
    @Column(name="task_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;

    @Column(name="description", nullable = false)
    private String description;

    @ManyToOne()
    @JoinColumn(name="user_id")
    @JsonBackReference
    private User user;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name="status")
    private Boolean status;
}
