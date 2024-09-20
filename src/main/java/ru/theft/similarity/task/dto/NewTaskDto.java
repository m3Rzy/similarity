package ru.theft.similarity.task.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class NewTaskDto {
    private String titleNewTaskDto;

    @Override
    public String toString() {
        return "Новая задача — " + titleNewTaskDto;
    }
}
