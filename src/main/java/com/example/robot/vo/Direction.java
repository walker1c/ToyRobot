package com.example.robot.vo;

import com.example.robot.exception.UnknownCommandException;

public enum Direction {
    NORTH(0, 1),
    EAST(1, 0),
    SOUTH(0, -1),
    WEST(-1, 0);

    private final int deltaX;
    private final int deltaY;

    Direction(int deltaX, int deltaY) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    public static Direction forName(String name) {
        try {
            return Direction.valueOf(name.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException ex) {
            throw new UnknownCommandException(name);
        }
    }

    public Position next(Position position) {
        return new Position(position.getXPos() + deltaX, position.getYPos() + deltaY);
    }

    public Direction left() {
        int index = (this.ordinal() + values().length - 1) % values().length;
        return values()[index];
    }

    public Direction right() {
        int index = (this.ordinal() + 1) % values().length;
        return values()[index];
    }
}
