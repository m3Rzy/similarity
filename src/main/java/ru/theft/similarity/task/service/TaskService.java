package ru.theft.similarity.task.service;

import org.springframework.data.domain.Page;
import ru.theft.similarity.task.dto.NewTaskDto;
import ru.theft.similarity.task.dto.TaskDto;
import ru.theft.similarity.task.model.Task;
import ru.theft.similarity.task.model.TaskStatus;

public interface TaskService {
    Page<Task> getAllWithPaginationAndSort(int page, int size, String sortDirection);

    TaskDto getById(long taskId);

    Task add(NewTaskDto newTaskDto);

    void changeStatus(long taskId, TaskStatus status);

    void delete(long taskId);
}
