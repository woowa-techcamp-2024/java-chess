package pieces;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PieceTest {

    public static final String WHITE = "white";

    @Test
    public void create_piece() {
        verifyPiece(Piece.createWhitePawn(), Piece.WHITE_COLOR, PieceType.PAWN.getRepresentation());
        verifyPiece(Piece.createBlackPawn(), Piece.BLACK_COLOR, PieceType.PAWN.getRepresentation());
        verifyPiece(Piece.createWhiteKnight(), Piece.WHITE_COLOR, PieceType.KNIGHT.getRepresentation());
        verifyPiece(Piece.createBlackKnight(), Piece.BLACK_COLOR, PieceType.KNIGHT.getRepresentation());
        verifyPiece(Piece.createWhiteRook(), Piece.WHITE_COLOR, PieceType.ROOK.getRepresentation());
        verifyPiece(Piece.createBlackRook(), Piece.BLACK_COLOR, PieceType.ROOK.getRepresentation());
        verifyPiece(Piece.createWhiteBishop(), Piece.WHITE_COLOR, PieceType.BISHOP.getRepresentation());
        verifyPiece(Piece.createBlackBishop(), Piece.BLACK_COLOR, PieceType.BISHOP.getRepresentation());
        verifyPiece(Piece.createWhiteQueen(), Piece.WHITE_COLOR, PieceType.QUEEN.getRepresentation());
        verifyPiece(Piece.createBlackQueen(), Piece.BLACK_COLOR, PieceType.QUEEN.getRepresentation());
        verifyPiece(Piece.createWhiteKing(), Piece.WHITE_COLOR, PieceType.KING.getRepresentation());
        verifyPiece(Piece.createBlackKing(), Piece.BLACK_COLOR, PieceType.KING.getRepresentation());
    }

    private void verifyPiece(Piece piece, String color, char representation) {
        assertEquals(piece.getColor(), color);
        assertEquals(piece.getRepresentation(),
            "white".equals(color) ? Character.toLowerCase(representation) : representation);
    }

    @Test
    void testColor() {
        assertTrue(Piece.createWhitePawn().isWhite());
        assertTrue(Piece.createBlackPawn().isBlack());
        assertTrue(Piece.createWhiteRook().isWhite());
        assertTrue(Piece.createBlackRook().isBlack());
        assertTrue(Piece.createWhiteKnight().isWhite());
        assertTrue(Piece.createBlackKnight().isBlack());
        assertTrue(Piece.createWhiteBishop().isWhite());
        assertTrue(Piece.createBlackBishop().isBlack());
        assertTrue(Piece.createWhiteQueen().isWhite());
        assertTrue(Piece.createBlackQueen().isBlack());
        assertTrue(Piece.createWhiteKing().isWhite());
        assertTrue(Piece.createBlackKing().isBlack());
    }
}
