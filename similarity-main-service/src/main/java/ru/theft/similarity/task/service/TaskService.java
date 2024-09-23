package ru.theft.similarity.task.service;

import org.springframework.data.domain.Page;
import ru.theft.similarity.task.dto.*;
import ru.theft.similarity.task.model.Task;

public interface TaskService {
    Page<Task> getAllWithPaginationAndSort(int page, int size, String sortDirection);

    TaskDto getById(long taskId);

    Task add(NewTaskDto newTaskDto);

    void changeStatus(long taskId, String status);

    void delete(long taskId);
}
