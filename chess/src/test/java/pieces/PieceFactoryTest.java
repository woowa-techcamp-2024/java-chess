package pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PieceFactoryTest {

    @Test
    @DisplayName("기물이 생성되어야 한다")
    public void create_piece()
    {
        PieceFactory pieceFactory = new PieceFactory();
        verifyPiece(pieceFactory.createBlackPiece(PieceType.PAWN), PieceColor.BLACK, PieceUnicode.BLACK_PAWN);
        verifyPiece(pieceFactory.createBlackPiece(PieceType.KNIGHT), PieceColor.BLACK, PieceUnicode.BLACK_KNIGHT);
        verifyPiece(pieceFactory.createBlackPiece(PieceType.BISHOP), PieceColor.BLACK, PieceUnicode.BLACK_BISHOP);
        verifyPiece(pieceFactory.createBlackPiece(PieceType.ROOK), PieceColor.BLACK, PieceUnicode.BLACK_ROOK);
        verifyPiece(pieceFactory.createBlackPiece(PieceType.QUEEN), PieceColor.BLACK, PieceUnicode.BLACK_QUEEN);
        verifyPiece(pieceFactory.createBlackPiece(PieceType.KING), PieceColor.BLACK, PieceUnicode.BLACK_KING);

        verifyPiece(pieceFactory.createWhitePiece(PieceType.PAWN), PieceColor.WHITE, PieceUnicode.WHITE_PAWN);
        verifyPiece(pieceFactory.createWhitePiece(PieceType.KNIGHT), PieceColor.WHITE, PieceUnicode.WHITE_KNIGHT);
        verifyPiece(pieceFactory.createWhitePiece(PieceType.BISHOP), PieceColor.WHITE, PieceUnicode.WHITE_BISHOP);
        verifyPiece(pieceFactory.createWhitePiece(PieceType.ROOK), PieceColor.WHITE, PieceUnicode.WHITE_ROOK);
        verifyPiece(pieceFactory.createWhitePiece(PieceType.QUEEN), PieceColor.WHITE, PieceUnicode.WHITE_QUEEN);
        verifyPiece(pieceFactory.createWhitePiece(PieceType.KING), PieceColor.WHITE, PieceUnicode.WHITE_KING);

        verifyPiece(pieceFactory.createBlank(), PieceColor.NO_COLOR, PieceUnicode.BLANK);
    }

    private void verifyPiece(final Piece piece, final PieceColor color, final PieceUnicode pieceUnicode){
        assertEquals(color, piece.color());
        assertEquals(pieceUnicode, piece.represent());
    }
}
