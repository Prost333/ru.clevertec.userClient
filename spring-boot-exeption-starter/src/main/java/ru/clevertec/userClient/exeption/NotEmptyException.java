package ru.clevertec.userClient.exeption;

public class NotEmptyException extends RuntimeException {

    public NotEmptyException(String message) {
        super(message);
    }
}