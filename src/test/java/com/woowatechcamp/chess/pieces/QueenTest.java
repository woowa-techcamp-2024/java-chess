package com.woowatechcamp.chess.pieces;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class QueenTest {
    @Test
    public void 여왕이_직선_또는_대각선으로_이동해야_한다() {
        Queen queen = new Queen(Piece.Color.WHITE, new Position("d1"));

        queen.move(new Position("d8")); // Valid move
        assertThat(queen.getPosition()).isEqualTo(new Position("d8"));

        queen.move(new Position("h4")); // Valid move
        assertThat(queen.getPosition()).isEqualTo(new Position("h4"));

        assertThatThrownBy(() -> queen.move(new Position("c6")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Queen can only move in straight lines or diagonals.");
    }
}
