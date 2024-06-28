package com.woowatechcamp.chess.pieces;

import com.woowatechcamp.chess.game.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class KnightTest {
    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
        board.initializeEmpty();
    }

    @Test
    public void 나이트가_L자_모양으로_이동할_수_있다() {
        Knight knight = new Knight(Piece.Color.WHITE, new Position("d4"));
        board.move(knight);

        // 모든 가능한 L자 이동을 테스트
        String[] validMoves = {"c6", "e6", "f5", "f3", "e2", "c2", "b3", "b5"};
        for (String move : validMoves) {
            board.move(new Position("d4"), new Position(move));
            assertThat(board.findPiece(new Position(move)).getType()).isEqualTo(Piece.Type.KNIGHT);
            // 다시 원위치로
            board.move(new Position(move), new Position("d4"));
        }
    }

    @Test
    public void 나이트가_L자_모양이_아닌_경로로_이동하면_예외를_던진다() {
        Knight knight = new Knight(Piece.Color.WHITE, new Position("d4"));
        board.move(knight);

        // L자 모양이 아닌 이동 시도
        assertThatThrownBy(() -> board.move(new Position("d4"), new Position("d5")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Knight can only move in an L shape.");

        assertThatThrownBy(() -> board.move(new Position("d4"), new Position("e5")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Knight can only move in an L shape.");
    }

    @Test
    public void 나이트가_같은_자리로_이동하면_예외를_던진다() {
        Knight knight = new Knight(Piece.Color.WHITE, new Position("d4"));
        board.move(knight);

        assertThatThrownBy(() -> board.move(new Position("d4"), new Position("d4")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Position occupied by same color");
    }

    @Test
    public void 나이트가_같은_색의_기물이_있는_위치로_이동하면_예외를_던진다() {
        Knight knight = new Knight(Piece.Color.WHITE, new Position("d4"));
        board.move(knight);
        Pawn pawn = new Pawn(Piece.Color.WHITE, new Position("c6"));
        board.move(pawn);

        assertThatThrownBy(() -> board.move(new Position("d4"), new Position("c6")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Position occupied by same color");
    }

    @Test
    public void 나이트가_다른_색의_기물을_잡을_수_있다() {
        Knight knight = new Knight(Piece.Color.WHITE, new Position("d4"));
        board.move(knight);
        Pawn pawn = new Pawn(Piece.Color.BLACK, new Position("c6"));
        board.move(pawn);

        board.move(new Position("d4"), new Position("c6"));
        assertThat(board.findPiece(new Position("c6")).getType()).isEqualTo(Piece.Type.KNIGHT);
    }

    @Test
    public void 나이트는_다른_기물을_뛰어넘을_수_있다() {
        Knight knight = new Knight(Piece.Color.WHITE, new Position("d4"));
        board.move(knight);
        Pawn pawn1 = new Pawn(Piece.Color.WHITE, new Position("d5"));
        Pawn pawn2 = new Pawn(Piece.Color.BLACK, new Position("e4"));
        board.move(pawn1);
        board.move(pawn2);

        // 나이트는 d5와 e4의 폰을 뛰어넘어 f5로 이동할 수 있어야 함
        board.move(new Position("d4"), new Position("f5"));
        assertThat(board.findPiece(new Position("f5")).getType()).isEqualTo(Piece.Type.KNIGHT);
    }
}
