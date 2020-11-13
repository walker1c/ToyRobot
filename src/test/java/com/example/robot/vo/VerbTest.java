package com.example.robot.vo;

import com.example.robot.exception.UnknownCommandException;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class VerbTest {
    
    @Test
    public void shouldReturnVerb_forName() {
        Object place = Verb.forName("PLACE");
        Object move = Verb.forName("MOVE");
        Object left = Verb.forName("LEFT");
        Object right = Verb.forName("RIGHT");
        Object report = Verb.forName("REPORT");
        Object quit = Verb.forName("QUIT");
        Object help = Verb.forName("HELP");

        assertThat(place).isEqualTo(Verb.PLACE);
        assertThat(move).isEqualTo(Verb.MOVE);
        assertThat(left).isEqualTo(Verb.LEFT);
        assertThat(right).isEqualTo(Verb.RIGHT);
        assertThat(report).isEqualTo(Verb.REPORT);
        assertThat(quit).isEqualTo(Verb.QUIT);
        assertThat(help).isEqualTo(Verb.HELP);
    }

    @Test
    public void shouldReturnVerb_forLowercaseName() {
        Object place = Verb.forName("place");
        Object move = Verb.forName("move");
        Object left = Verb.forName("left");
        Object right = Verb.forName("right");
        Object report = Verb.forName("report");
        Object quit = Verb.forName("quit");
        Object help = Verb.forName("help");

        assertThat(place).isEqualTo(Verb.PLACE);
        assertThat(move).isEqualTo(Verb.MOVE);
        assertThat(left).isEqualTo(Verb.LEFT);
        assertThat(right).isEqualTo(Verb.RIGHT);
        assertThat(report).isEqualTo(Verb.REPORT);
        assertThat(quit).isEqualTo(Verb.QUIT);
        assertThat(help).isEqualTo(Verb.HELP);
    }

    @Test(expected = UnknownCommandException.class)
    public void shouldThrowException_forUnknownName() {
        Verb what = Verb.forName("shift");
    }

    @Test(expected = UnknownCommandException.class)
    public void shouldThrowException_forNullName() {
        Verb what = Verb.forName(null);
    }
}
