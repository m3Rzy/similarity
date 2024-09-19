package ru.theft.similarity.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.theft.similarity.task.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
