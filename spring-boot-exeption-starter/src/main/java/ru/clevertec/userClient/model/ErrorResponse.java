package ru.clevertec.userClient.model;

import lombok.Data;

@Data
public class ErrorResponse {
    private String message;
    private String code;

}
