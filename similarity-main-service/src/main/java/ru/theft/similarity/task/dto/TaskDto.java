package ru.theft.similarity.task.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.theft.similarity.task.model.TaskStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskDto {
    private String titleTaskDto;
    private TaskStatus statusTaskDto;
    private String createdAtTaskDto;
}
