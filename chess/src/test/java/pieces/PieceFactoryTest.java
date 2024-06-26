package pieces;

import chess.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PieceFactoryTest {

    @Test
    @DisplayName("기물이 생성되어야 한다")
    public void create_piece() throws Exception {
        PieceFactory pieceFactory = new PieceFactory();
        verifyPiece(pieceFactory.createBlackPawn(new Position("a1")), PieceColor.BLACK, PieceType.PAWN, PieceUnicode.BLACK_PAWN);
        verifyPiece(pieceFactory.createBlackKnight(new Position("a1")), PieceColor.BLACK, PieceType.KNIGHT, PieceUnicode.BLACK_KNIGHT);
        verifyPiece(pieceFactory.createBlackBishop(new Position("a1")), PieceColor.BLACK, PieceType.BISHOP, PieceUnicode.BLACK_BISHOP);
        verifyPiece(pieceFactory.createBlackRook(new Position("a1")), PieceColor.BLACK, PieceType.ROOK, PieceUnicode.BLACK_ROOK);
        verifyPiece(pieceFactory.createBlackQueen(new Position("a1")), PieceColor.BLACK, PieceType.QUEEN, PieceUnicode.BLACK_QUEEN);
        verifyPiece(pieceFactory.createBlackKing(new Position("a1")), PieceColor.BLACK, PieceType.KING, PieceUnicode.BLACK_KING);

        verifyPiece(pieceFactory.createWhitePawn(new Position("a1")), PieceColor.WHITE, PieceType.PAWN, PieceUnicode.WHITE_PAWN);
        verifyPiece(pieceFactory.createWhiteKnight(new Position("a1")), PieceColor.WHITE, PieceType.KNIGHT, PieceUnicode.WHITE_KNIGHT);
        verifyPiece(pieceFactory.createWhiteBishop(new Position("a1")), PieceColor.WHITE, PieceType.BISHOP, PieceUnicode.WHITE_BISHOP);
        verifyPiece(pieceFactory.createWhiteRook(new Position("a1")), PieceColor.WHITE, PieceType.ROOK, PieceUnicode.WHITE_ROOK);
        verifyPiece(pieceFactory.createWhiteQueen(new Position("a1")), PieceColor.WHITE, PieceType.QUEEN, PieceUnicode.WHITE_QUEEN);
        verifyPiece(pieceFactory.createWhiteKing(new Position("a1")), PieceColor.WHITE, PieceType.KING, PieceUnicode.WHITE_KING);

        verifyPiece(pieceFactory.createBlank(new Position("a1")), PieceColor.NO_COLOR, PieceType.NO_PIECE, PieceUnicode.BLANK);
    }

    private void verifyPiece(final Piece piece, final PieceColor color, final PieceType type, final PieceUnicode pieceUnicode) throws Exception {
        assertEquals(new Piece(color, type, new Position("a1")), piece);
        assertEquals(color, piece.getColor());
        assertEquals(type, piece.getType());
        assertEquals(pieceUnicode.getUnicode(), piece.getRepresent());
    }
}
