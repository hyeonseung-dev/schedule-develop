package com.example.scheduledevelop.global.exception;

import org.springframework.http.HttpStatus;

/**
 * 등록되지 않은 id의 유저
 */

public class UserNotFoundException extends ServiceException {
    public UserNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND,message);
    }
}
