package com.example.robot.service;

import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class SystemIoService implements IoService {
    private final PrintStream out;
    private final BufferedReader reader;

    SystemIoService(InputStream in, PrintStream out) {
        this.reader = new BufferedReader(new InputStreamReader(in));
        this.out = out;
    }

    public SystemIoService() {
        this(System.in, System.out);
    }

    @Override
    public String readLine(String format, Object... args) throws IOException {
        System.out.printf(format, args);
        return reader.readLine();
    }

    @Override
    public IoService printf(String format, Object... args) {
        System.out.printf(format, args);
        return this;
    }
}
