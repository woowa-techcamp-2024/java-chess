package pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PieceFactoryTest {

    @Test
    @DisplayName("기물이 생성되어야 한다")
    public void create_piece() {
        PieceFactory pieceFactory = new PieceFactory();
        verifyPiece(pieceFactory.createBlackPawn(), PieceColor.BLACK, PieceType.PAWN, PieceUnicode.BLACK_PAWN);
        verifyPiece(pieceFactory.createBlackKnight(), PieceColor.BLACK, PieceType.KNIGHT, PieceUnicode.BLACK_KNIGHT);
        verifyPiece(pieceFactory.createBlackBishop(), PieceColor.BLACK, PieceType.BISHOP, PieceUnicode.BLACK_BISHOP);
        verifyPiece(pieceFactory.createBlackRook(), PieceColor.BLACK, PieceType.ROOK, PieceUnicode.BLACK_ROOK);
        verifyPiece(pieceFactory.createBlackQueen(), PieceColor.BLACK, PieceType.QUEEN, PieceUnicode.BLACK_QUEEN);
        verifyPiece(pieceFactory.createBlackKing(), PieceColor.BLACK, PieceType.KING, PieceUnicode.BLACK_KING);

        verifyPiece(pieceFactory.createWhitePawn(), PieceColor.WHITE, PieceType.PAWN, PieceUnicode.WHITE_PAWN);
        verifyPiece(pieceFactory.createWhiteKnight(), PieceColor.WHITE, PieceType.KNIGHT, PieceUnicode.WHITE_KNIGHT);
        verifyPiece(pieceFactory.createWhiteBishop(), PieceColor.WHITE, PieceType.BISHOP, PieceUnicode.WHITE_BISHOP);
        verifyPiece(pieceFactory.createWhiteRook(), PieceColor.WHITE, PieceType.ROOK, PieceUnicode.WHITE_ROOK);
        verifyPiece(pieceFactory.createWhiteQueen(), PieceColor.WHITE, PieceType.QUEEN, PieceUnicode.WHITE_QUEEN);
        verifyPiece(pieceFactory.createWhiteKing(), PieceColor.WHITE, PieceType.KING, PieceUnicode.WHITE_KING);

        verifyPiece(pieceFactory.createBlank(), PieceColor.NO_COLOR, PieceType.NO_PIECE, PieceUnicode.BLANK);
    }

    private void verifyPiece(final Piece piece, final PieceColor color, final PieceType type, final PieceUnicode pieceUnicode) {
        assertEquals(new Piece(color, type), piece);
        assertEquals(color, piece.color());
        assertEquals(type, piece.type());
        assertEquals(pieceUnicode.getUnicode(), piece.getRepresent());
    }
}
