package com.example.robot;

import com.example.robot.exception.InvalidPositionException;
import com.example.robot.service.TableTop;
import com.example.robot.vo.Direction;
import com.example.robot.vo.Position;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Log
public class Robot {
    private TableTop tableTop;
    private Position position;
    private Direction direction;

    @Autowired
    public Robot(TableTop tableTop) {
        this.tableTop = tableTop;
    }

    public void place(int xPos, int yPos, Direction direction) {
        this.position = checkPosition(new Position(xPos, yPos));
        this.direction = direction;
        log.info("placed: " + report());
    }

    public void left() {
        checkState();
        direction = direction.left();
        log.info("turned left: " + report());
    }

    public void right() {
        checkState();
        direction = direction.right();
        log.info("turned right: " + report());
    }

    public void move() {
        checkState();
        position = checkPosition(direction.next(position));
        log.info("moved: " + report());
    }

    public String report() {
        checkState();
        return String.format("%s,%s", position, direction);
    }

    private Position checkPosition(Position position) {
        if (!tableTop.hasPosition(position)) {
            throw new InvalidPositionException(position);
        }
        return position;
    }

    private void checkState() {
        if (position == null || direction == null) {
            throw new IllegalStateException("The robot has not been positioned");
        }
    }
}
