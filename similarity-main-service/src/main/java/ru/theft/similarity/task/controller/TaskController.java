package ru.theft.similarity.task.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.theft.similarity.task.dto.*;
import ru.theft.similarity.task.mapper.TaskMapper;
import ru.theft.similarity.task.model.*;
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
    public ResponseEntity<List<Task>> getAllTaskWithPaginationAndSort(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size,
            @RequestParam(value = "sort", required = false) String sortDirection) {
        return ResponseEntity
                .ok(taskService
                        .getAllWithPaginationAndSort(page, size, sortDirection)
                        .stream()
                        .toList());
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable long taskId) {
        return ResponseEntity
                .ok(taskService.getById(taskId));
    }

    @PatchMapping("/{taskId}")
    public ResponseEntity<?> changeTaskStatus(@PathVariable long taskId,
                                           @RequestParam(value = "status") String statusTitle) {
        taskService.changeStatus(taskId, statusTitle);
        return ResponseEntity
                .ok("Статус задачи успешно изменился!");
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<?> deleteTaskById(@PathVariable long taskId) {
        taskService.delete(taskId);
        return ResponseEntity
                .ok("Задача была успешно удалена!");
    }

    @PostMapping
    public ResponseEntity<TaskDto> createNewTask(@RequestBody NewTaskDto newTaskDto) {
        TaskDto taskDto = mapper.mapToDto(taskService.add(newTaskDto));

        RestTemplate restTemplate = new RestTemplate();
        String emailServiceUrl = "http://similarity-mail-service:8081/email";
        String recipient = "demeiz@yandex.ru";
        String subject = "Задача успешно создана!";

        String emailRequestUrl = emailServiceUrl + "?to=" + recipient + "&subject=" + subject
                + "&taskTitle=" + taskDto.getTitleTaskDto() + "&created=" + taskDto.getCreatedAtTaskDto()
                + "&status=" + taskDto.getStatusTaskDto().getStatusTitle();

        restTemplate.postForEntity(emailRequestUrl, null, String.class);
        return ResponseEntity
                .status(201)
                .body(taskDto);
    }
}
