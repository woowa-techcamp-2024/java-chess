package com.woowatechcamp.chess.pieces;

import com.woowatechcamp.chess.game.Board;
import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

public class BishopTest {
    private Board board;

    @Before
    public void setUp() {
        board = new Board();
        board.initializeEmpty();
    }

    @Test
    public void 비숍이_대각선으로_이동해야_한다() {
        Bishop bishop = new Bishop(Piece.Color.WHITE, new Position("c1"));
        board.move(bishop);

        board.move(new Position("c1"), new Position("a3"));
        assertThat(board.findPiece(new Position("a3")).getType()).isEqualTo(Piece.Type.BISHOP);

        board.move(new Position("a3"), new Position("e7"));
        assertThat(board.findPiece(new Position("e7")).getType()).isEqualTo(Piece.Type.BISHOP);
    }

    @Test
    public void 비숍이_대각선이_아닌_경로로_이동하면_예외를_던진다() {
        Bishop bishop = new Bishop(Piece.Color.WHITE, new Position("c1"));
        board.move(bishop);

        assertThatThrownBy(() -> board.move(new Position("c1"), new Position("e4")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Bishop can only move diagonally.");
    }

    @Test
    public void 비숍이_같은_자리로_이동하면_예외를_던진다() {
        Bishop bishop = new Bishop(Piece.Color.WHITE, new Position("c1"));
        board.move(bishop);

        assertThatThrownBy(() -> board.move(new Position("c1"), new Position("c1")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Position occupied by same color");
    }

    @Test
    public void 비숍의_경로에_다른_기물이_있으면_예외를_던진다() {
        Bishop bishop = new Bishop(Piece.Color.WHITE, new Position("c1"));
        board.move(bishop);
        Pawn pawn = new Pawn(Piece.Color.WHITE, new Position("d2"));
        board.move(pawn);

        assertThatThrownBy(() -> board.move(new Position("c1"), new Position("e3")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Path is not clear.");
    }

    @Test
    public void 비숍이_같은_색의_기물이_있는_위치로_이동하면_예외를_던진다() {
        Bishop bishop = new Bishop(Piece.Color.WHITE, new Position("c1"));
        board.move(bishop);
        Pawn pawn = new Pawn(Piece.Color.WHITE, new Position("e3"));
        board.move(pawn);

        assertThatThrownBy(() -> board.move(new Position("c1"), new Position("e3")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Position occupied by same color");
    }

    @Test
    public void 비숍이_다른_색의_기물을_잡을_수_있다() {
        Bishop bishop = new Bishop(Piece.Color.WHITE, new Position("c1"));
        board.move(bishop);
        Pawn pawn = new Pawn(Piece.Color.BLACK, new Position("e3"));
        board.move(pawn);

        board.move(new Position("c1"), new Position("e3"));
        assertThat(board.findPiece(new Position("e3")).getType()).isEqualTo(Piece.Type.BISHOP);
    }
}
