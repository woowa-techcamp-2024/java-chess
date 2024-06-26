package com.woowatechcamp.chess.pieces;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BishopTest {
    @Test
    public void 비숍이_대각선으로_이동해야_한다() {
        Bishop bishop = new Bishop(Piece.Color.WHITE, new Position("c1"));

        bishop.move(new Position("a3")); // Valid diagonal move
        assertThat(bishop.getPosition()).isEqualTo(new Position("a3"));

        bishop.move(new Position("e7")); // Valid diagonal move
        assertThat(bishop.getPosition()).isEqualTo(new Position("e7"));

        assertThatThrownBy(() -> bishop.move(new Position("e4")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Bishop can only move diagonally.");
    }
}
