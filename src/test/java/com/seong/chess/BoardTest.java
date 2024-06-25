package com.seong.chess;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.seong.chess.pieces.Pawn;
import com.seong.chess.pieces.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    @DisplayName("체스 보드에 폰을 추가하면 입력 순서대로 추가된다.")
    public void create() {
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
    public void initialize() {
        board.initialize();
        assertEquals("pppppppp", board.getWhitePawnsResult());
        assertEquals("PPPPPPPP", board.getBlackPawnsResult());
    }

    @Test
    @DisplayName("체스 보드를 출력하면 체스 보드 모양대로 출력된다.")
    public void printChessBoard() {
        board.initialize();
        String result = board.print();
        assertThat(result)
                .isEqualTo(
                        "........\n"
                                + "PPPPPPPP\n"
                                + "........\n"
                                + "........\n"
                                + "........\n"
                                + "........\n"
                                + "pppppppp\n"
                                + "........"
                );
        System.out.println(result);
    }
}
