package com.example.robot;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log
public class Application implements CommandLineRunner {

    @Autowired
    private Interpreter interpreter;

    @Override
    public void run(String... strings) throws Exception {
        interpreter.listen();
    }

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Application.class);
        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.run(args);
    }
}
