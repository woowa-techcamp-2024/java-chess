package pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PieceFactoryTest {

    @Test
    @DisplayName("기물이 생성되어야 한다")
    public void create_piece() {
        PieceFactory pieceFactory = new PieceFactory();
        verifyPiece(pieceFactory.createPiece(PieceColor.BLACK, PieceType.PAWN), PieceColor.BLACK, PieceType.PAWN, PieceUnicode.BLACK_PAWN);
        verifyPiece(pieceFactory.createPiece(PieceColor.BLACK, PieceType.KNIGHT), PieceColor.BLACK, PieceType.KNIGHT, PieceUnicode.BLACK_KNIGHT);
        verifyPiece(pieceFactory.createPiece(PieceColor.BLACK, PieceType.BISHOP), PieceColor.BLACK, PieceType.BISHOP, PieceUnicode.BLACK_BISHOP);
        verifyPiece(pieceFactory.createPiece(PieceColor.BLACK, PieceType.ROOK), PieceColor.BLACK, PieceType.ROOK, PieceUnicode.BLACK_ROOK);
        verifyPiece(pieceFactory.createPiece(PieceColor.BLACK, PieceType.QUEEN), PieceColor.BLACK, PieceType.QUEEN, PieceUnicode.BLACK_QUEEN);
        verifyPiece(pieceFactory.createPiece(PieceColor.BLACK, PieceType.KING), PieceColor.BLACK, PieceType.KING, PieceUnicode.BLACK_KING);

        verifyPiece(pieceFactory.createPiece(PieceColor.WHITE, PieceType.PAWN), PieceColor.WHITE, PieceType.PAWN, PieceUnicode.WHITE_PAWN);
        verifyPiece(pieceFactory.createPiece(PieceColor.WHITE, PieceType.KNIGHT), PieceColor.WHITE, PieceType.KNIGHT, PieceUnicode.WHITE_KNIGHT);
        verifyPiece(pieceFactory.createPiece(PieceColor.WHITE, PieceType.BISHOP), PieceColor.WHITE, PieceType.BISHOP, PieceUnicode.WHITE_BISHOP);
        verifyPiece(pieceFactory.createPiece(PieceColor.WHITE, PieceType.ROOK), PieceColor.WHITE, PieceType.ROOK, PieceUnicode.WHITE_ROOK);
        verifyPiece(pieceFactory.createPiece(PieceColor.WHITE, PieceType.QUEEN), PieceColor.WHITE, PieceType.QUEEN, PieceUnicode.WHITE_QUEEN);
        verifyPiece(pieceFactory.createPiece(PieceColor.WHITE, PieceType.KING), PieceColor.WHITE, PieceType.KING, PieceUnicode.WHITE_KING);

        verifyPiece(pieceFactory.createBlank(), PieceColor.NO_COLOR, PieceType.NO_PIECE, PieceUnicode.BLANK);
    }

    private void verifyPiece(final Piece piece, final PieceColor color, final PieceType type, final PieceUnicode pieceUnicode) {
        assertEquals(color, piece.color());
        assertEquals(type, piece.type());
        assertEquals(pieceUnicode.getUnicode(), piece.getRepresent());
    }
}
