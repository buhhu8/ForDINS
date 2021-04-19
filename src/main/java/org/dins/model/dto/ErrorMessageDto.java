package org.dins.model.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorMessageDto {

    private final LocalDateTime timestamp;
    private final int status;
    private final String message;

    public ErrorMessageDto(int status, String message) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
    }

}
