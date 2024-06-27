package org.example.chess.board;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PositionTest {

    @Test
    void testDelta() {
        Position pos1 = new Position(3, 3);
        Position pos2 = new Position(0, 0);

        // 최대공약수로 나눈 델타값 확인
        Position delta = pos1.delta(pos2);
        assertThat(delta).isEqualTo(new Position(1, 1));

        pos1 = new Position(6, 3);
        pos2 = new Position(3, 0);
        delta = pos1.delta(pos2);
        assertThat(delta).isEqualTo(new Position(1, 1));

        pos1 = new Position(3, 3);
        pos2 = new Position(0, 3);
        delta = pos1.delta(pos2);
        assertThat(delta).isEqualTo(new Position(1, 0));

        pos1 = new Position(3, 3);
        pos2 = new Position(3, 6);
        delta = pos1.delta(pos2);
        assertThat(delta).isEqualTo(new Position(0, -1));

        pos1 = new Position(3, 3);
        pos2 = new Position(3, 0);
        delta = pos1.delta(pos2);
        assertThat(delta).isEqualTo(new Position(0, 1));

        pos1 = new Position(5, 5);
        pos2 = new Position(0, 0);
        delta = pos1.delta(pos2);
        assertThat(delta).isEqualTo(new Position(1, 1));

        pos1 = new Position(0, 0);
        pos2 = new Position(5, 0);
        delta = pos1.delta(pos2);
        assertThat(delta).isEqualTo(new Position(-1, 0));

        pos1 = new Position(0, 0);
        pos2 = new Position(2, -1);
        delta = pos1.delta(pos2);
        assertThat(delta).isEqualTo(new Position(-2, 1));
    }
}
