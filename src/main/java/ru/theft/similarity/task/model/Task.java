package ru.theft.similarity.task.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import ru.theft.similarity.utils.Constant;

import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "task_title", nullable = false)
    private String title;

    @Column(name = "task_status", nullable = false)
    private TaskStatus status;

    @Column(name = "task_created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "task_changed_at")
    @CreationTimestamp
//    todo: убрать автоматическое создание времени
    private LocalDateTime changedAt;

    @Column(name = "task_closed_at")
    @CreationTimestamp
//    todo: убрать автоматическое создание времени
    private LocalDateTime closedAt;

    @Override
    public String toString() {
        return "Задача " + id + " — " + title + " была успешно создана сегодня в "
                + createdAt.format(Constant.dateFormatter);
    }
}
