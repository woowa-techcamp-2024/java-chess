package chess.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.pieces.Piece.Type;
import static org.junit.jupiter.api.Assertions.*;

class PieceTest {


    @DisplayName("정적 팩토리 메서드를 이용하여 원하는 폰을 생성할 수 있다")
    @Test
    void create_piece() {
        verifyPiece(Pawn.createWhitePawn(), Pawn.createBlackPawn(), Type.PAWN);
        verifyPiece(Knight.createWhiteKnight(), Knight.createBlackKnight(), Type.KNIGHT);
        verifyPiece(Rook.createWhiteRook(), Rook.createBlackRook(), Type.ROOK);
        verifyPiece(Bishop.createWhiteBishop(), Bishop.createBlackBishop(), Type.BISHOP);
        verifyPiece(Queen.createWhiteQueen(), Queen.createBlackQueen(), Type.QUEEN);
        verifyPiece(King.createWhiteKing(), King.createBlackKing(), Type.KING);

        Piece blank = Blank.createBlank();
        assertFalse(blank.isWhite());
        assertFalse(blank.isBlack());
//        assertEquals(Type.NO_PIECE, blank.getType());

    }


    private void verifyPiece(final Piece whitePiece, final Piece blackPiece, final Type type) {
        assertTrue(whitePiece.isWhite());
        assertEquals(type, whitePiece.getType());

        assertTrue(blackPiece.isBlack());
        assertEquals(type, blackPiece.getType());
    }

}
