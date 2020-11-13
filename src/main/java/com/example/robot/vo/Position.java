package com.example.robot.vo;

import lombok.Data;

@Data
public class Position {
    private final int xPos;
    private final int yPos;

    @Override
    public String toString() {
        return String.format("%d,%d", xPos, yPos);
    }
}
