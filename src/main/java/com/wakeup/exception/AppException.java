package com.wakeup.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AppException extends RuntimeException{
    private ErrorCode errorCodes;
    private String message;
}
