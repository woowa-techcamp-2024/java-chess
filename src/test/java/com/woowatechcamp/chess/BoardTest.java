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

    @Test
    public void 기물을_이동시킨다() {
        board.initializeEmpty();

        String position = "b5";
        Piece piece = Piece.createBlackRook();
        board.move(position, piece);

         assertThat(piece).isEqualTo(board.findPiece(position));
        System.out.println(board.showBoard());
    }

    @Test
    public void 기물의_점수를_계산한다() {
        board.initializeEmpty();

        addPiece("b6", Piece.createBlackPawn());
        addPiece("e6", Piece.createBlackQueen());
        addPiece("b8", Piece.createBlackKing());
        addPiece("c8", Piece.createBlackRook());

        addPiece("f2", Piece.createWhitePawn());
        addPiece("g2", Piece.createWhitePawn());
        addPiece("e1", Piece.createWhiteRook());
        addPiece("f1", Piece.createWhiteKing());

        assertThat(15.0).isEqualTo(board.calculatePoint(Piece.Color.BLACK));
        assertThat(7.0).isEqualTo(board.calculatePoint(Piece.Color.WHITE));
    }

    private void addPiece(String position, Piece piece) {
        board.move(position, piece);
    }
}
