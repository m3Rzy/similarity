package ru.theft.similarity.task.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import ru.theft.similarity.task.dto.*;
import ru.theft.similarity.task.mapper.TaskMapper;
import ru.theft.similarity.task.model.*;
import ru.theft.similarity.task.repository.TaskRepository;
import ru.theft.similarity.task.service.TaskService;
import ru.theft.similarity.utils.exception.NotFoundException;

import java.time.LocalDateTime;

import static ru.theft.similarity.task.model.TaskStatus.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    @Autowired
    private TaskMapper mapper;

    @Override
    public Page<Task> getAllWithPaginationAndSort(int page, int size, String sortDirection) {
        log.info("Количество задач: {}", taskRepository.findAll().size());

        return taskRepository.findAll(formPaginationAndSort(page, size, sortDirection));
    }

    @Override
    public TaskDto getById(long taskId) {
        return mapper.mapToDto(searchTaskById(taskId));
    }

    @Override
    public Task add(NewTaskDto newTaskDto) {
        Task task = Task.builder()
                .title(newTaskDto.getTitleNewTaskDto())
                .status(OPEN)
                .createdAt(LocalDateTime.now())
                .changedAt(null)
                .closedAt(null)
                .build();
        log.info("{} была успешно создана!", newTaskDto);
        return taskRepository.save(task);
    }

    @Override
    public void changeStatus(long taskId, String status) {
        Task task = searchTaskById(taskId);
        task.setStatus(TaskStatus.valueOf(status));
        taskRepository.saveAndFlush(task);

        log.info("Статус {} был успешно изменен!", task);
    }

    @Override
    public void delete(long taskId) {
        Task task = searchTaskById(taskId);
        taskRepository.delete(task);

        log.info("{} была успешно удалена!", task);
    }

    private Task searchTaskById(long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new NotFoundException("Задачи с " + taskId + " id не существует!"));
    }

    private Pageable formPaginationAndSort(int page, int size, String sortDirection) {
        Pageable pageable;
        if (sortDirection != null) {
            if (sortDirection.equals("DESC")) {
                pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
            } else {
                pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id"));
            }
        } else {
            pageable = PageRequest.of(page, size);
        }
        return pageable;
    }
}
