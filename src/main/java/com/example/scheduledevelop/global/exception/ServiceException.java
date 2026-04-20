package com.example.scheduledevelop.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
/**
 * 글로벌 서비스 계층 예외처리
 */
public class ServiceException extends RuntimeException{
    /* HTTP 상태코드 응답을 담는 필드 */
    private final HttpStatus status;

    /* HTTP 상태코드 응답을 부모(RuntimeException)에 메시지 전달 */
    public ServiceException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

}
