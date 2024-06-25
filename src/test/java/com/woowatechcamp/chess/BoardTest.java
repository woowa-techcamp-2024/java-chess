package com.woowatechcamp.chess;

import static com.woowatechcamp.utils.StringUtils.appendNewLine;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import com.woowatechcamp.chess.pieces.Piece;
import org.junit.Before;
import org.junit.Test;

public class BoardTest {
    private Board board;

    @Before
    public void setUp() {
        board = new Board();
    }

    @Test
    public void initialize() throws Exception {
        board.initialize();
        assertEquals(32, board.pieceCount());

        String blankRank = appendNewLine("........");
        String expected = appendNewLine("♜♞♝♛♚♝♞♜") + appendNewLine("♟♟♟♟♟♟♟♟") +
                blankRank + blankRank + blankRank + blankRank +
                appendNewLine("♙♙♙♙♙♙♙♙") + appendNewLine("♖♘♗♕♔♗♘♖");
        assertThat(expected).isEqualTo(board.showBoard());
    }

    @Test
    public void 기물과_색상에_해당하는_기물의_개수를_반환한다() {
        board.initialize();
        assertThat(1).isEqualTo(board.pieceCount(Piece.Type.KING, Piece.Color.WHITE));
        assertThat(1).isEqualTo(board.pieceCount(Piece.Type.QUEEN, Piece.Color.WHITE));
        assertThat(8).isEqualTo(board.pieceCount(Piece.Type.PAWN, Piece.Color.WHITE));

        assertThat(1).isEqualTo(board.pieceCount(Piece.Type.KING, Piece.Color.BLACK));
        assertThat(1).isEqualTo(board.pieceCount(Piece.Type.QUEEN, Piece.Color.BLACK));
        assertThat(8).isEqualTo(board.pieceCount(Piece.Type.PAWN, Piece.Color.BLACK));
    }

    @Test
    public void 빈_체스판을_생성한다() {
        board.initializeEmpty();
        String blankRank = appendNewLine("........");
        assertThat(blankRank + blankRank + blankRank + blankRank +
                blankRank + blankRank + blankRank + blankRank).isEqualTo(board.showBoard());
    }
}
