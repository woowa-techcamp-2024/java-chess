package com.woowatechcamp.chess.pieces;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class KingTest {

    @Test
    public void 왕이_한_칸_이동해야_한다() {
        King king = new King(Piece.Color.WHITE, new Position("e1"));

        king.move(new Position("e2"));
        assertThat(king.getPosition()).isEqualTo(new Position("e2"));

        king.move(new Position("d2"));
        assertThat(king.getPosition()).isEqualTo(new Position("d2"));

        assertThatThrownBy(() -> king.move(new Position("d4")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("King can only move one square in any direction.");
    }
}
