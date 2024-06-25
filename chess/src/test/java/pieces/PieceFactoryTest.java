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
        verifyPiece(pieceFactory.createBlackPawn(), PieceColor.BLACK, PieceUnicode.BLACK_PAWN);
        verifyPiece(pieceFactory.createBlackKnight(), PieceColor.BLACK, PieceUnicode.BLACK_KNIGHT);
        verifyPiece(pieceFactory.createBlackBishop(), PieceColor.BLACK, PieceUnicode.BLACK_BISHOP);
        verifyPiece(pieceFactory.createBlackRook(), PieceColor.BLACK, PieceUnicode.BLACK_ROOK);
        verifyPiece(pieceFactory.createBlackQueen(), PieceColor.BLACK, PieceUnicode.BLACK_QUEEN);
        verifyPiece(pieceFactory.createBlackKing(), PieceColor.BLACK, PieceUnicode.BLACK_KING);

        verifyPiece(pieceFactory.createWhitePawn(), PieceColor.WHITE, PieceUnicode.WHITE_PAWN);
        verifyPiece(pieceFactory.createWhiteKnight(), PieceColor.WHITE, PieceUnicode.WHITE_KNIGHT);
        verifyPiece(pieceFactory.createWhiteBishop(), PieceColor.WHITE, PieceUnicode.WHITE_BISHOP);
        verifyPiece(pieceFactory.createWhiteRook(), PieceColor.WHITE, PieceUnicode.WHITE_ROOK);
        verifyPiece(pieceFactory.createWhiteQueen(), PieceColor.WHITE, PieceUnicode.WHITE_QUEEN);
        verifyPiece(pieceFactory.createWhiteKing(), PieceColor.WHITE, PieceUnicode.WHITE_KING);
    }

    private void verifyPiece(final Piece piece, final PieceColor color, final PieceUnicode pieceUnicode){
        assertEquals(color, piece.color());
        assertEquals(pieceUnicode, piece.represent());
    }
}
