package com.example.scheduledevelop.global.exception;

import org.springframework.http.HttpStatus;

/**
 * 인가되지 않았을때 예외처리
 */

public class UnauthorizedException extends ServiceException{
    public UnauthorizedException(String message) {
        super(HttpStatus.UNAUTHORIZED,message);
    }
}
