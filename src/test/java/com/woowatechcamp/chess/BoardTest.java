package com.woowatechcamp.chess;

import static com.woowatechcamp.utils.StringUtils.appendNewLine;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import com.woowatechcamp.chess.pieces.Piece;
import com.woowatechcamp.chess.pieces.PieceFactory;
import com.woowatechcamp.chess.pieces.Position;
import org.junit.Before;
import org.junit.Test;

public class BoardTest {
    private Board board;

    @Before
    public void setUp() {
        board = new Board();
    }

    @Test
    public void initialize() {
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
    public void 기물을_추가한다() {
        board.initializeEmpty();

        Position position = new Position("b5");
        Piece piece = PieceFactory.createBlackRook(position);
        board.move(piece);

         assertThat(piece).isEqualTo(board.findPiece(position));
        System.out.println(board.showBoard());
    }

    @Test
    public void 기물의_점수를_계산한다() {
        board.initializeEmpty();

        addPiece(PieceFactory.createBlackPawn(new Position("b6")));
        addPiece(PieceFactory.createBlackQueen(new Position("e6")));
        addPiece(PieceFactory.createBlackKing(new Position("b8")));
        addPiece(PieceFactory.createBlackRook(new Position("c8")));

        addPiece(PieceFactory.createWhitePawn(new Position("f2")));
        addPiece(PieceFactory.createWhitePawn(new Position("g2")));
        addPiece(PieceFactory.createWhiteRook(new Position("e1")));
        addPiece(PieceFactory.createWhiteKing(new Position("f1")));

        assertThat(15.0).isEqualTo(board.calculatePoint(Piece.Color.BLACK));
        assertThat(7.0).isEqualTo(board.calculatePoint(Piece.Color.WHITE));
    }

    private void addPiece(Piece piece) {
        board.move(piece);
    }

    @Test
    public void 기물을_이동시킬_수_있다() {
        board.initialize();

        Position sourcePosition = new Position("b2");
        Position targetPosition = new Position("b3");
        board.move(sourcePosition, targetPosition);
        assertThat(PieceFactory.createBlank(sourcePosition)).isEqualTo(board.findPiece(sourcePosition));
        assertThat(PieceFactory.createWhitePawn(targetPosition)).isEqualTo(board.findPiece(targetPosition));

        board.print();
    }
}
