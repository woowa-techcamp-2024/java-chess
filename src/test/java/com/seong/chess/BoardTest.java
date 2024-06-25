package com.seong.chess;

import com.seong.chess.pieces.Pawn;
import com.seong.chess.pieces.Piece;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {

    @Test
    @DisplayName("체스 보드에 폰을 추가하면 입력 순서대로 추가된다.")
    public void create() {
        Board board = new Board();

        Pawn white = addPawn(board, Piece.Colors.WHITE_COLOR);
        assertThat(board.size()).isEqualTo(1);
        assertThat(board.findPawn(0)).isEqualTo(white);

        Pawn black = addPawn(board, Piece.Colors.BLACK_COLOR);
        assertThat(board.size()).isEqualTo(2);
        assertThat(board.findPawn(1)).isEqualTo(black);
    }

    private Pawn addPawn(Board board, String color) {
        Pawn pawn = new Pawn(color);
        board.add(pawn);
        return pawn;
    }
}
