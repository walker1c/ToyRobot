package com.example.robot.service;

import com.example.robot.vo.Position;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TableTop {

    private final int height = 5;
    private final int width = 5;

    public boolean hasPosition(Position position) {
        return Optional.ofNullable(position)
                .map(pos ->
                        pos.getXPos() >= 0 && pos.getXPos() < width &&
                        pos.getYPos() >= 0 && pos.getYPos() < height)
                .orElse(false);
    }
}
