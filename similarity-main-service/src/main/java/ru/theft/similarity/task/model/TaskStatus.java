package ru.theft.similarity.task.model;

import lombok.*;

@AllArgsConstructor
@Getter
public enum TaskStatus {

    OPEN("Открыт"),
    IN_PROGRESS("В процессе"),
    CLOSED("Закрыт");

    private final String statusTitle;
}
