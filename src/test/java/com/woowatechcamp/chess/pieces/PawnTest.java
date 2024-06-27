package com.woowatechcamp.chess.pieces;

import com.woowatechcamp.chess.Board;
import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

public class PawnTest {
    private Board board;

    @Before
    public void setUp() {
        board = new Board();
        board.initializeEmpty();
    }

    @Test
    public void 백색_폰은_앞으로_한_칸_이동할_수_있다() {
        Pawn pawn = new Pawn(Piece.Color.WHITE, new Position("e2"));
        board.move(pawn);

        board.move(new Position("e2"), new Position("e3"));
        assertThat(board.findPiece(new Position("e3")).getType()).isEqualTo(Piece.Type.PAWN);
    }

    @Test
    public void 흑색_폰은_앞으로_한_칸_이동할_수_있다() {
        Pawn pawn = new Pawn(Piece.Color.BLACK, new Position("e7"));
        board.move(pawn);

        board.move(new Position("e7"), new Position("e6"));
        assertThat(board.findPiece(new Position("e6")).getType()).isEqualTo(Piece.Type.PAWN);
    }

    @Test
    public void 폰은_첫_이동시_두_칸_이동할_수_있다() {
        Pawn whitePawn = new Pawn(Piece.Color.WHITE, new Position("e2"));
        board.move(whitePawn);
        board.move(new Position("e2"), new Position("e4"));
        assertThat(board.findPiece(new Position("e4")).getType()).isEqualTo(Piece.Type.PAWN);

        Pawn blackPawn = new Pawn(Piece.Color.BLACK, new Position("d7"));
        board.move(blackPawn);
        board.move(new Position("d7"), new Position("d5"));
        assertThat(board.findPiece(new Position("d5")).getType()).isEqualTo(Piece.Type.PAWN);
    }

    @Test
    public void 폰은_첫_이동_이후_두_칸_이동할_수_없다() {
        Pawn pawn = new Pawn(Piece.Color.WHITE, new Position("e2"));
        board.move(pawn);
        board.move(new Position("e2"), new Position("e3"));

        assertThatThrownBy(() -> board.move(new Position("e3"), new Position("e5")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Can only move two squares on first move");
    }

    @Test
    public void 폰은_대각선으로_적_기물을_잡을_수_있다() {
        Pawn whitePawn = new Pawn(Piece.Color.WHITE, new Position("e4"));
        board.move(whitePawn);
        Pawn blackPawn = new Pawn(Piece.Color.BLACK, new Position("d5"));
        board.move(blackPawn);

        board.move(new Position("e4"), new Position("d5"));
        assertThat(board.findPiece(new Position("d5")).getType()).isEqualTo(Piece.Type.PAWN);
        assertThat(board.findPiece(new Position("d5")).getColor()).isEqualTo(Piece.Color.WHITE);
    }

    @Test
    public void 폰은_대각선으로_이동할_수_없다() {
        Pawn pawn = new Pawn(Piece.Color.WHITE, new Position("e4"));
        board.move(pawn);

        assertThatThrownBy(() -> board.move(new Position("e4"), new Position("d5")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Pawn can only move diagonally when capturing");
    }

    @Test
    public void 폰은_후진할_수_없다() {
        Pawn whitePawn = new Pawn(Piece.Color.WHITE, new Position("e4"));
        board.move(whitePawn);
        assertThatThrownBy(() -> board.move(new Position("e4"), new Position("e3")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Pawn cannot move backwards");

        Pawn blackPawn = new Pawn(Piece.Color.BLACK, new Position("d5"));
        board.move(blackPawn);
        assertThatThrownBy(() -> board.move(new Position("d5"), new Position("d6")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Pawn cannot move backwards");
    }

    @Test
    public void 폰은_전방에_기물이_있으면_이동할_수_없다() {
        Pawn whitePawn = new Pawn(Piece.Color.WHITE, new Position("e4"));
        board.move(whitePawn);
        Pawn blackPawn = new Pawn(Piece.Color.BLACK, new Position("e5"));
        board.move(blackPawn);

        assertThatThrownBy(() -> board.move(new Position("e4"), new Position("e5")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Cannot move to occupied position");
    }
}
