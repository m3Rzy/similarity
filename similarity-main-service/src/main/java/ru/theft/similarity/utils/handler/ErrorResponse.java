package ru.theft.similarity.utils.handler;

import lombok.*;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private String title;
    private Integer status_code;
    private String description;
}
