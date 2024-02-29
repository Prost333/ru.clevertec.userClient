package ru.clevertec.userClient.exeption;


import ru.clevertec.userClient.model.ErrorResponse;

public interface ExceptionHandler {
    ErrorResponse handleException(Exception e);
}
