package ru.theft.similarity.utils.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.theft.similarity.utils.exception.NotFoundException;


@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handle(NotFoundException e) {
        return new ErrorResponse("Data not found.", HttpStatus.NOT_FOUND.value(),
                e.getDescription());
    }
}
