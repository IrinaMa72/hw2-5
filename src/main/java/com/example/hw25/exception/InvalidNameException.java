package com.example.hw25.exception;

public class InvalidNameException extends RuntimeException{

    public InvalidNameException(String massage) {
        super(massage);
    }
}
