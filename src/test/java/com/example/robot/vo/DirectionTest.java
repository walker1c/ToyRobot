package com.example.robot.vo;

import com.example.robot.exception.UnknownCommandException;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DirectionTest {
    
    @Test
    public void shouldMovePosition_whenNorth() {
        Position position1 = new Position(2, 3);

        Position position2 = Direction.NORTH.next(position1);

        assertThat(position2.getXPos()).isEqualTo(2);
        assertThat(position2.getYPos()).isEqualTo(4);
    }

    @Test
    public void shouldMovePosition_whenEast() {
        Position position1 = new Position(2, 3);

        Position position2 = Direction.EAST.next(position1);

        assertThat(position2.getXPos()).isEqualTo(3);
        assertThat(position2.getYPos()).isEqualTo(3);
    }

    @Test
    public void shouldMovePosition_whenSouth() {
        Position position1 = new Position(2, 3);

        Position position2 = Direction.SOUTH.next(position1);

        assertThat(position2.getXPos()).isEqualTo(2);
        assertThat(position2.getYPos()).isEqualTo(2);
    }

    @Test
    public void shouldMovePosition_whenWest() {
        Position position1 = new Position(2, 3);

        Position position2 = Direction.WEST.next(position1);

        assertThat(position2.getXPos()).isEqualTo(1);
        assertThat(position2.getYPos()).isEqualTo(3);
    }

    @Test
    public void shouldReturnNextDirectionLeft() {
        Direction direction1 = Direction.NORTH.left();
        Direction direction2 = Direction.EAST.left();
        Direction direction3 = Direction.SOUTH.left();
        Direction direction4 = Direction.WEST.left();

        assertThat(direction1).isEqualTo(Direction.WEST);
        assertThat(direction2).isEqualTo(Direction.NORTH);
        assertThat(direction3).isEqualTo(Direction.EAST);
        assertThat(direction4).isEqualTo(Direction.SOUTH);
    }

    @Test
    public void shouldReturnNextDirectionRight() {
        Direction direction1 = Direction.NORTH.right();
        Direction direction2 = Direction.EAST.right();
        Direction direction3 = Direction.SOUTH.right();
        Direction direction4 = Direction.WEST.right();

        assertThat(direction1).isEqualTo(Direction.EAST);
        assertThat(direction2).isEqualTo(Direction.SOUTH);
        assertThat(direction3).isEqualTo(Direction.WEST);
        assertThat(direction4).isEqualTo(Direction.NORTH);
    }

    @Test
    public void shouldReturnDirection_forName() {
        Object north = Direction.forName("NORTH");
        Object east = Direction.forName("EAST");
        Object south = Direction.forName("SOUTH");
        Object west = Direction.forName("WEST");

        assertThat(north).isEqualTo(Direction.NORTH);
        assertThat(east).isEqualTo(Direction.EAST);
        assertThat(south).isEqualTo(Direction.SOUTH);
        assertThat(west).isEqualTo(Direction.WEST);
    }

    @Test
    public void shouldReturnDirection_forLowercaseName() {
        Object north = Direction.forName("north");
        Object east = Direction.forName("east");
        Object south = Direction.forName("south");
        Object west = Direction.forName("west");

        assertThat(north).isEqualTo(Direction.NORTH);
        assertThat(east).isEqualTo(Direction.EAST);
        assertThat(south).isEqualTo(Direction.SOUTH);
        assertThat(west).isEqualTo(Direction.WEST);
    }

    @Test(expected = UnknownCommandException.class)
    public void shouldThrowException_forUnknownName() {
        Direction what = Direction.forName("NORTHWEST");
    }

    @Test(expected = UnknownCommandException.class)
    public void shouldThrowException_forNullName() {
        Direction what = Direction.forName(null);
    }
}
