package com.example.robot.service;

import java.io.IOException;

public interface IoService {
    String readLine(String format, Object... args) throws IOException;
    IoService printf(String format, Object... args);
}
