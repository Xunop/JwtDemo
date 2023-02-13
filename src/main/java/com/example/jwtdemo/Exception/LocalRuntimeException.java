package com.example.jwtdemo.Exception;

import lombok.Data;

/**
 * @Author xun
 * @create 2023/2/13
 */
@Data
public class LocalRuntimeException extends RuntimeException{
    public LocalRuntimeException(String message) {super(message);}
}
