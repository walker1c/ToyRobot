package com.example.robot.service;

import com.example.robot.exception.UnknownCommandException;
import com.example.robot.vo.Position;
import com.example.robot.vo.Verb;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TableTopTest {

    TableTop tableTop = new TableTop();
    
    @Test
    public void shouldReturnTrue_whenX_and_Y_inRange() {
        boolean result = tableTop.hasPosition(new Position(3, 4));
        assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalse_whenX_and_Y_outOfRange() {
        boolean result = tableTop.hasPosition(new Position(99, 88));
        assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalse_whenX_outOfRange() {
        boolean result = tableTop.hasPosition(new Position(456, 4));
        assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalse_whenY_outOfRange() {
        boolean result = tableTop.hasPosition(new Position(0, -1));
        assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalse_whenPosition_isNull() {
        boolean result = tableTop.hasPosition(null);
        assertThat(result).isFalse();
    }
}
