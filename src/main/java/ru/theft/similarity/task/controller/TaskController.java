package ru.theft.similarity.task.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.theft.similarity.task.dto.NewTaskDto;
import ru.theft.similarity.task.dto.TaskDto;
import ru.theft.similarity.task.mapper.TaskMapper;
import ru.theft.similarity.task.model.Task;
import ru.theft.similarity.task.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/v1/task")
@AllArgsConstructor
public class TaskController {
    private TaskService taskService;
    @Autowired
    private TaskMapper mapper;

    @GetMapping
    public List<Task> getAllTaskWithPaginationAndSort(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size,
            @RequestParam(value = "sort", required = false) String sortDirection) {
        return taskService.getAllWithPaginationAndSort(page, size, sortDirection)
                .stream()
                .toList();
    }

    @PostMapping
    public TaskDto createNewTask(@RequestBody NewTaskDto newTaskDto) {
        return mapper.mapToDto(taskService.add(newTaskDto));
    }

//    todo: дописать все контроллеры
}
