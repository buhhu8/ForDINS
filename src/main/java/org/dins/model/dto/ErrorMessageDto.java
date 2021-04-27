package org.dins.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorMessageDto {

    @ApiModelProperty(name = "timestamp", value = "Return time when error has happened ")
    private final LocalDateTime timestamp;
    @ApiModelProperty(name = "status", value = "Return HTTP status")
    private final int status;
    @ApiModelProperty(name = "message", value = "Return error message")
    private final String message;

    public ErrorMessageDto(int status, String message) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
    }

}
