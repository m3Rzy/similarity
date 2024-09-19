package ru.theft.similarity.task.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.annotation.Configuration;
import ru.theft.similarity.task.dto.TaskDto;
import ru.theft.similarity.task.model.Task;
import ru.theft.similarity.utils.Constant;

@Configuration
@AllArgsConstructor
@Data
public class TaskMapper {
    public TaskDto mapToDto(Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setTitleTaskDto(task.getTitle());
        taskDto.setStatusTaskDto(task.getStatus());
        taskDto.setCreatedAtTaskDto(task.getCreatedAt().format(Constant.dateFormatter));
        return taskDto;
    }
}
