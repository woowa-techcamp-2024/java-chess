package com.woowatechcamp.chess.pieces;

import com.woowatechcamp.chess.game.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class RookTest {
    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
        board.initializeEmpty();
    }

    @Test
    public void 룩이_수직으로_이동할_수_있다() {
        Rook rook = new Rook(Piece.Color.WHITE, new Position("d4"));
        board.move(rook);

        board.move(new Position("d4"), new Position("d8"));
        assertThat(board.findPiece(new Position("d8")).getType()).isEqualTo(Piece.Type.ROOK);
    }

    @Test
    public void 룩이_수평으로_이동할_수_있다() {
        Rook rook = new Rook(Piece.Color.WHITE, new Position("d4"));
        board.move(rook);

        board.move(new Position("d4"), new Position("h4"));
        assertThat(board.findPiece(new Position("h4")).getType()).isEqualTo(Piece.Type.ROOK);
    }

    @Test
    public void 룩이_대각선으로_이동하면_예외를_던진다() {
        Rook rook = new Rook(Piece.Color.WHITE, new Position("d4"));
        board.move(rook);

        assertThatThrownBy(() -> board.move(new Position("d4"), new Position("f6")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Rook can only move in straight lines.");
    }

    @Test
    public void 룩이_같은_자리로_이동하면_예외를_던진다() {
        Rook rook = new Rook(Piece.Color.WHITE, new Position("d4"));
        board.move(rook);

        assertThatThrownBy(() -> board.move(new Position("d4"), new Position("d4")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Position occupied by same color");
    }

    @Test
    public void 룩의_경로에_다른_기물이_있으면_예외를_던진다() {
        Rook rook = new Rook(Piece.Color.WHITE, new Position("d4"));
        board.move(rook);
        Pawn pawn = new Pawn(Piece.Color.WHITE, new Position("d6"));
        board.move(pawn);

        assertThatThrownBy(() -> board.move(new Position("d4"), new Position("d8")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Path is not clear.");
    }

    @Test
    public void 룩이_같은_색의_기물이_있는_위치로_이동하면_예외를_던진다() {
        Rook rook = new Rook(Piece.Color.WHITE, new Position("d4"));
        board.move(rook);
        Pawn pawn = new Pawn(Piece.Color.WHITE, new Position("d8"));
        board.move(pawn);

        assertThatThrownBy(() -> board.move(new Position("d4"), new Position("d8")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Position occupied by same color");
    }

    @Test
    public void 룩이_다른_색의_기물을_잡을_수_있다() {
        Rook rook = new Rook(Piece.Color.WHITE, new Position("d4"));
        board.move(rook);
        Pawn pawn = new Pawn(Piece.Color.BLACK, new Position("d8"));
        board.move(pawn);

        board.move(new Position("d4"), new Position("d8"));
        assertThat(board.findPiece(new Position("d8")).getType()).isEqualTo(Piece.Type.ROOK);
    }
}
