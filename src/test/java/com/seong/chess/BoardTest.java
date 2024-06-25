package com.seong.chess;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.seong.chess.pieces.Pawn;
import com.seong.chess.pieces.Piece;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

    @Test
    @DisplayName("체스 보드를 초기화하면 폰이 배치된다.")
    public void initialize() throws Exception {
        Board board = new Board();
        board.initialize();
        assertEquals("pppppppp", board.getWhitePawnsResult());
        assertEquals("PPPPPPPP", board.getBlackPawnsResult());
    }
}
