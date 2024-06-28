package chess.pieces;

import org.junit.jupiter.api.*;
import chess.pieces.Piece.Type;

import static org.junit.jupiter.api.Assertions.*;

public class PieceTest {


    @Test
    public void getRepresentationPerPiece() throws Exception {
        assertEquals('p', Piece.Type.PAWN.getWhiteRepresentation());
        assertEquals('P', Piece.Type.PAWN.getBlackRepresentation());
    }

    @Test
    @DisplayName("Piece가 생성되어야 한다.")
    public void create_piece() {
        verifyPiece(Pawn.createWhitePawn(), Pawn.createBlackPawn(), Type.PAWN);
        verifyPiece(Knight.createWhiteKnight(), Knight.createBlackKnight(), Type.KNIGHT);
        verifyPiece(Rook.createWhiteRook(), Rook.createBlackRook(), Type.ROOK);
        verifyPiece(Bishop.createWhiteBishop(), Bishop.createBlackBishop(), Type.BISHOP);
        verifyPiece(Queen.createWhiteQueen(), Queen.createBlackQueen(), Type.QUEEN);
        verifyPiece(King.createWhiteKing(), King.createBlackKing(), Type.KING);

        Piece blank = Blank.createBlank();
        assertFalse(blank.isWhite());
        assertFalse(blank.isBlack());
        assertEquals(Type.NO_PIECE, blank.getType());

    }

    private void verifyPiece(final Piece whitePiece, final Piece blackPiece, final Type type) {
        assertTrue(whitePiece.isWhite());
        assertEquals(type, whitePiece.getType());

        assertTrue(blackPiece.isBlack());
        assertEquals(type, whitePiece.getType());
    }

    @Test
    @DisplayName("검은색 말과 흰색 말을 구분할 수 있어야한다.")
    public void checkColor(){
        Piece piece = Pawn.createWhitePawn();
        assertFalse(piece.isBlack());
        assertTrue(piece.isWhite());
    }

}
