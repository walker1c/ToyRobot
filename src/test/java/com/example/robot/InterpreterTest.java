package com.example.robot;

import com.example.robot.service.IoService;
import com.example.robot.vo.Direction;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class InterpreterTest {

    Interpreter interpreter;
    IoService mockIo;
    Robot mockRobot;

    @Before
    public void setup() {
        mockIo = mock(IoService.class);
        mockRobot = mock(Robot.class);
        interpreter = new Interpreter(mockIo, mockRobot);
    }

    @Test(timeout = 5000)
    public void shouldPlaceRobot_whenInstructed() throws IOException {
        when(mockIo.readLine(anyString(), any()))
                .thenReturn("PLACE 3,2,WEST")
                .thenReturn("QUIT");

        interpreter.listen();

        verify(mockRobot).place(3, 2, Direction.WEST);
    }

    @Test(timeout = 5000)
    public void shouldTellRobot_turnLeft() throws IOException {
        when(mockIo.readLine(anyString(), any()))
                .thenReturn("PLACE 3,2,WEST")
                .thenReturn("LEFT")
                .thenReturn("QUIT");

        interpreter.listen();

        verify(mockRobot).left();
    }

    @Test(timeout = 5000)
    public void shouldTellRobot_turnRight() throws IOException {
        when(mockIo.readLine(anyString(), any()))
                .thenReturn("PLACE 3,2,WEST")
                .thenReturn("RIGHT")
                .thenReturn("QUIT");

        interpreter.listen();

        verify(mockRobot).right();
    }

    @Test(timeout = 5000)
    public void shouldTellRobot_move() throws IOException {
        when(mockIo.readLine(anyString(), any()))
                .thenReturn("PLACE 3,2,WEST")
                .thenReturn("MOVE")
                .thenReturn("QUIT");

        interpreter.listen();

        verify(mockRobot).move();
    }

    @Test(timeout = 5000)
    public void shouldTellRobot_report() throws IOException {
        when(mockIo.readLine(anyString(), any()))
                .thenReturn("PLACE 3,2,WEST")
                .thenReturn("REPORT")
                .thenReturn("QUIT");
        when(mockRobot.report()).thenReturn("This is your robot reporting");

        interpreter.listen();

        verify(mockIo).printf("%s%n", "This is your robot reporting");
    }

    @Test(timeout = 5000)
    public void shouldShowHelp_whenCommandArgumentsMissing() throws IOException {
        when(mockIo.readLine(anyString(), any()))
                .thenReturn("PLACE 3,2")
                .thenReturn("QUIT");

        interpreter.listen();

        verify(mockIo).printf("%s%n", "Expected more input");
        verify(mockRobot, never()).place(anyInt(), anyInt(), any(Direction.class));
    }

    @Test(timeout = 5000)
    public void shouldShowHelp_whenInvalidCommand() throws IOException {
        when(mockIo.readLine(anyString(), any()))
                .thenReturn("PLAACE 3,2,WEST")
                .thenReturn("QUIT");

        interpreter.listen();

        verify(mockIo).printf("%s%n", "Unknown command: PLAACE");
        verify(mockRobot, never()).place(3, 2,Direction.WEST);
    }
}
