package com.woowatechcamp.chess.pieces;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class KnightTest {
    @Test
    public void 나이트가_L자_형태로_이동해야_한다() {
        Knight knight = new Knight(Piece.Color.WHITE, new Position("b1"));

        knight.move(new Position("c3")); // Valid L-shaped move
        assertThat(knight.getPosition()).isEqualTo(new Position("c3"));

        knight.move(new Position("e2")); // Valid L-shaped move
        assertThat(knight.getPosition()).isEqualTo(new Position("e2"));

        assertThatThrownBy(() -> knight.move(new Position("d3")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Knight can only move in an L shape.");
    }
}
