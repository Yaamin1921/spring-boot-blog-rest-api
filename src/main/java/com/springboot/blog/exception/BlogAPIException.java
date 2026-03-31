package com.springboot.blog.exception;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public class BlogAPIException extends RuntimeException{
    private HttpStatus httpStatus;
    private String message;
}
