package com.woowatechcamp.chess.pieces;

import com.woowatechcamp.chess.game.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class QueenTest {
    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
        board.initializeEmpty();
    }

    @Test
    public void 퀸이_수직으로_이동할_수_있다() {
        Queen queen = new Queen(Piece.Color.WHITE, new Position("d4"));
        board.move(queen);

        board.move(new Position("d4"), new Position("d8"));
        assertThat(board.findPiece(new Position("d8")).getType()).isEqualTo(Piece.Type.QUEEN);
    }

    @Test
    public void 퀸이_수평으로_이동할_수_있다() {
        Queen queen = new Queen(Piece.Color.WHITE, new Position("d4"));
        board.move(queen);

        board.move(new Position("d4"), new Position("h4"));
        assertThat(board.findPiece(new Position("h4")).getType()).isEqualTo(Piece.Type.QUEEN);
    }

    @Test
    public void 퀸이_대각선으로_이동할_수_있다() {
        Queen queen = new Queen(Piece.Color.WHITE, new Position("d4"));
        board.move(queen);

        board.move(new Position("d4"), new Position("h8"));
        assertThat(board.findPiece(new Position("h8")).getType()).isEqualTo(Piece.Type.QUEEN);
    }

    @Test
    public void 퀸이_L자_모양으로_이동하면_예외를_던진다() {
        Queen queen = new Queen(Piece.Color.WHITE, new Position("d4"));
        board.move(queen);

        assertThatThrownBy(() -> board.move(new Position("d4"), new Position("e6")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Queen can only move in straight lines or diagonals.");
    }

    @Test
    public void 퀸이_같은_자리로_이동하면_예외를_던진다() {
        Queen queen = new Queen(Piece.Color.WHITE, new Position("d4"));
        board.move(queen);

        assertThatThrownBy(() -> board.move(new Position("d4"), new Position("d4")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Position occupied by same color");
    }

    @Test
    public void 퀸의_경로에_다른_기물이_있으면_예외를_던진다() {
        Queen queen = new Queen(Piece.Color.WHITE, new Position("d4"));
        board.move(queen);
        Pawn pawn = new Pawn(Piece.Color.WHITE, new Position("f6"));
        board.move(pawn);

        assertThatThrownBy(() -> board.move(new Position("d4"), new Position("h8")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Path is not clear.");
    }

    @Test
    public void 퀸이_같은_색의_기물이_있는_위치로_이동하면_예외를_던진다() {
        Queen queen = new Queen(Piece.Color.WHITE, new Position("d4"));
        board.move(queen);
        Pawn pawn = new Pawn(Piece.Color.WHITE, new Position("d8"));
        board.move(pawn);

        assertThatThrownBy(() -> board.move(new Position("d4"), new Position("d8")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Position occupied by same color");
    }

    @Test
    public void 퀸이_다른_색의_기물을_잡을_수_있다() {
        Queen queen = new Queen(Piece.Color.WHITE, new Position("d4"));
        board.move(queen);
        Pawn pawn = new Pawn(Piece.Color.BLACK, new Position("h8"));
        board.move(pawn);

        board.move(new Position("d4"), new Position("h8"));
        assertThat(board.findPiece(new Position("h8")).getType()).isEqualTo(Piece.Type.QUEEN);
    }
}
