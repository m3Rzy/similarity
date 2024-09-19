package ru.theft.similarity.task.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum TaskStatus {

    OPEN("Открыт"),
    IN_PROGRESS("В процессе"),
    CLOSED("Закрыт");

    private final String title;
}
