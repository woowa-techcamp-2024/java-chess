package com.woowatechcamp.chess.pieces;

import com.woowatechcamp.chess.game.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class KingTest {
    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
        board.initializeEmpty();
    }

    @Test
    public void 킹이_모든_방향으로_한_칸씩_이동할_수_있다() {
        King king = new King(Piece.Color.WHITE, new Position("e4"));
        board.move(king);

        // 상
        board.move(new Position("e4"), new Position("e5"));
        assertThat(board.findPiece(new Position("e5")).getType()).isEqualTo(Piece.Type.KING);

        // 우상
        board.move(new Position("e5"), new Position("f6"));
        assertThat(board.findPiece(new Position("f6")).getType()).isEqualTo(Piece.Type.KING);

        // 우
        board.move(new Position("f6"), new Position("g6"));
        assertThat(board.findPiece(new Position("g6")).getType()).isEqualTo(Piece.Type.KING);

        // 우하
        board.move(new Position("g6"), new Position("h5"));
        assertThat(board.findPiece(new Position("h5")).getType()).isEqualTo(Piece.Type.KING);

        // 하
        board.move(new Position("h5"), new Position("h4"));
        assertThat(board.findPiece(new Position("h4")).getType()).isEqualTo(Piece.Type.KING);

        // 좌하
        board.move(new Position("h4"), new Position("g3"));
        assertThat(board.findPiece(new Position("g3")).getType()).isEqualTo(Piece.Type.KING);

        // 좌
        board.move(new Position("g3"), new Position("f3"));
        assertThat(board.findPiece(new Position("f3")).getType()).isEqualTo(Piece.Type.KING);

        // 좌상
        board.move(new Position("f3"), new Position("e4"));
        assertThat(board.findPiece(new Position("e4")).getType()).isEqualTo(Piece.Type.KING);
    }

    @Test
    public void 킹이_두_칸_이상_이동하려고_하면_예외를_던진다() {
        King king = new King(Piece.Color.WHITE, new Position("e4"));
        board.move(king);

        assertThatThrownBy(() -> board.move(new Position("e4"), new Position("e6")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("King can only move one square in any direction.");

        assertThatThrownBy(() -> board.move(new Position("e4"), new Position("g6")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("No valid direction for the given positions");
    }

    @Test
    public void 킹이_같은_자리로_이동하면_예외를_던진다() {
        King king = new King(Piece.Color.WHITE, new Position("e4"));
        board.move(king);

        assertThatThrownBy(() -> board.move(new Position("e4"), new Position("e4")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Position occupied by same color");
    }

    @Test
    public void 킹이_같은_색의_기물이_있는_위치로_이동하면_예외를_던진다() {
        King king = new King(Piece.Color.WHITE, new Position("e4"));
        board.move(king);
        Pawn pawn = new Pawn(Piece.Color.WHITE, new Position("e5"));
        board.move(pawn);

        assertThatThrownBy(() -> board.move(new Position("e4"), new Position("e5")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Position occupied by same color");
    }

    @Test
    public void 킹이_다른_색의_기물을_잡을_수_있다() {
        King king = new King(Piece.Color.WHITE, new Position("e4"));
        board.move(king);
        Pawn pawn = new Pawn(Piece.Color.BLACK, new Position("e5"));
        board.move(pawn);

        board.move(new Position("e4"), new Position("e5"));
        assertThat(board.findPiece(new Position("e5")).getType()).isEqualTo(Piece.Type.KING);
    }
}
