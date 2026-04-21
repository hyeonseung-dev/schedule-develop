package com.example.scheduledevelop.global.exception;

import org.springframework.http.HttpStatus;

/**
 * 로그인 실패 예외처리
 */

public class LoginFailedException extends ServiceException{
    public LoginFailedException(String message){
        super(HttpStatus.UNAUTHORIZED,message);
    }
}
