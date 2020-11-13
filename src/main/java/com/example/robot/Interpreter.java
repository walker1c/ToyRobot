package com.example.robot;

import com.example.robot.exception.InvalidPositionException;
import com.example.robot.exception.UnknownCommandException;
import com.example.robot.service.IoService;
import com.example.robot.vo.Direction;
import com.example.robot.vo.Verb;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

@Service
@Log
public class Interpreter {

    private final IoService io;
    private final Robot robot;

    @Autowired
    public Interpreter(IoService io, Robot robot) {
        this.io = io;
        this.robot = robot;
    }

    public void listen() throws IOException {
        boolean done = false;
        while (!done) {
            String command = io.readLine("robot>");
            try (Scanner scanner = new Scanner(command)) {
                switch (Verb.forName(scanner.next())) {

                    case PLACE:
                        scanner.useDelimiter("[ ,]");
                        int xPos = scanner.nextInt();
                        int yPos = scanner.nextInt();
                        Direction direction = Direction.forName(scanner.next());
                        robot.place(xPos, yPos, direction);
                        break;

                    case MOVE:
                        robot.move();
                        break;

                    case LEFT:
                        robot.left();
                        break;

                    case RIGHT:
                        robot.right();
                        break;

                    case REPORT:
                        io.printf("%s%n", robot.report());
                        break;

                    case HELP:
                        help();
                        break;

                    case QUIT:
                        done = true;
                        break;
                }

            } catch (InvalidPositionException | UnknownCommandException | IllegalStateException ex) {
                help(ex.getMessage());
            } catch (NoSuchElementException ex) {
                help("Expected more input");
            }
        }
    }

    private void help(String message) {
        io.printf("%s%n", message);
        help();
    }
    private void help() {
        io.printf("You can enter these commands:%n");
        io.printf("    %s X,Y,F - position the robot%n", Verb.PLACE);
        io.printf("    %s - move the robot one unit forward in the direction it is currently facing%n", Verb.MOVE);
        io.printf("    %s - turn 90%% left%n", Verb.LEFT);
        io.printf("    %s - turn 90%% right%n", Verb.RIGHT);
        io.printf("    %s - report the current position and direction%n", Verb.REPORT);
        io.printf("    %s - shut down%n", Verb.QUIT);
        io.printf("    %s - show this message%n", Verb.HELP);
    }

}
