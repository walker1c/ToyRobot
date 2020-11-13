package com.example.robot.vo;

import com.example.robot.exception.UnknownCommandException;

import static java.lang.String.format;

public enum Verb {
    PLACE,
    MOVE,
    LEFT,
    RIGHT,
    REPORT,
    QUIT,
    HELP;

    public static Verb forName(String name) {
        try {
            return Verb.valueOf(name.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException ex) {
            throw new UnknownCommandException(name);
        }
    }
}
