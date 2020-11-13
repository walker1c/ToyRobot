package com.example.robot.exception;

import com.example.robot.vo.Position;

public class InvalidPositionException extends IllegalArgumentException {

    public InvalidPositionException(int xPos, int yPos) {
        super(String.format("Position: x=%d, y=%d is off the tabletop", xPos, yPos));
    }

    public InvalidPositionException(Position position) {
        this(position.getXPos(), position.getYPos());
    }
}
