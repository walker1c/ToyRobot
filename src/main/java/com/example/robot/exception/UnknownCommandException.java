package com.example.robot.exception;

public class UnknownCommandException extends IllegalArgumentException {

    public UnknownCommandException(String name) {
        super(String.format("Unknown command: %s", name));
    }
}
