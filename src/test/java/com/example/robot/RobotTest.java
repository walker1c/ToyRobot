package com.example.robot;

import com.example.robot.exception.InvalidPositionException;
import com.example.robot.service.TableTop;
import com.example.robot.vo.Direction;
import com.example.robot.vo.Position;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RobotTest {

    Robot robot;
    TableTop tableTop;

    @Before
    public void setup() {
        tableTop = mock(TableTop.class);
        when(tableTop.hasPosition(any(Position.class))).thenReturn(true);

        robot = new Robot(tableTop);
    }

    @Test
    public void shouldPlace_whenPositionValid_andDirectionValid() {
        robot.place(2,3, Direction.WEST);
        String report = robot.report();
        assertThat(report).isEqualTo("2,3,WEST");
    }

    @Test
    public void shouldTurnLeft() {
        robot.place(2,3, Direction.SOUTH);
        robot.left();
        String report = robot.report();

        assertThat(report).isEqualTo("2,3,EAST");
    }

    @Test
    public void shouldTurnRight() {
        robot.place(2,3, Direction.NORTH);
        robot.right();
        String report = robot.report();

        assertThat(report).isEqualTo("2,3,EAST");
    }

    @Test
    public void shouldMove_whenNewPositionValid() {
        robot.place(2,3, Direction.NORTH);
        robot.move();
        String report = robot.report();

        assertThat(report).isEqualTo("2,4,NORTH");
    }

    @Test(expected = InvalidPositionException.class)
    public void shouldNotMove_whenNewPositionInvalid() {
        robot.place(0,0, Direction.WEST);
        when(tableTop.hasPosition(any(Position.class))).thenReturn(false);
        robot.move();
    }

    @Test(expected = InvalidPositionException.class)
    public void shouldThrowException_whenPositionInvalid() {
        when(tableTop.hasPosition(any(Position.class))).thenReturn(false);
        robot.place(99,3, Direction.SOUTH);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowException_whenLeft_andDirectionNull() {
        robot.place(2,3, null);
        robot.left();
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowException_whenLeft_andPositionNull() {
        robot.left();
    }
    
    @Test(expected = IllegalStateException.class)
    public void shouldThrowException_whenRight_andDirectionNull() {
        robot.place(2,3, null);
        robot.right();
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowException_whenRight_andPositionNull() {
        robot.right();
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowException_whenMove_andDirectionNull() {
        robot.place(2,3, null);
        robot.move();
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowException_whenMove_andPositionNull() {
        robot.move();
    }
}
