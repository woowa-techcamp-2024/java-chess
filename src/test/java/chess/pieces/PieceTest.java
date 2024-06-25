package chess.pieces;

import org.junit.jupiter.api.Test;

import static chess.pieces.PieceTypes.*;
import static org.junit.jupiter.api.Assertions.*;

public class PieceTest {
    private void verifyPiece(final Piece piece, final PieceTypes type) {
        verifyPiece(piece, type.getColor(), type.getRepresentation(), type.getType());
    }

    private void verifyPiece(final Piece piece, final Color color, final char representation, final Type type) {
        assertEquals(color, piece.getColor());
        assertEquals(representation, piece.getRepresentation());
        assertEquals(type, piece.getType());
    }

    @Test
    public void create() {
        Piece whitePawn = Piece.createPiece(WHITE_PAWN);
        Piece blackPawn = Piece.createPiece(BLACK_PAWN);
        Piece whiteKnight = Piece.createPiece(WHITE_KNIGHT);
        Piece blackKnight = Piece.createPiece(BLACK_KNIGHT);
        Piece whiteRook = Piece.createPiece(WHITE_ROOK);
        Piece blackRook = Piece.createPiece(BLACK_ROOK);
        Piece whiteBishop = Piece.createPiece(WHITE_BISHOP);
        Piece blackBishop = Piece.createPiece(BLACK_BISHOP);
        Piece whiteQueen = Piece.createPiece(WHITE_QUEEN);
        Piece blackQueen = Piece.createPiece(BLACK_QUEEN);
        Piece whiteKing = Piece.createPiece(WHITE_KING);
        Piece blackKing = Piece.createPiece(BLACK_KING);

        verifyPiece(whitePawn, WHITE_PAWN);
        verifyPiece(blackPawn, BLACK_PAWN);
        verifyPiece(whiteKnight, WHITE_KNIGHT);
        verifyPiece(blackKnight, BLACK_KNIGHT);
        verifyPiece(whiteRook, WHITE_ROOK);
        verifyPiece(blackRook, BLACK_ROOK);
        verifyPiece(whiteBishop, WHITE_BISHOP);
        verifyPiece(blackBishop, BLACK_BISHOP);
        verifyPiece(whiteQueen, WHITE_QUEEN);
        verifyPiece(blackQueen, BLACK_QUEEN);
        verifyPiece(whiteKing, WHITE_KING);
        verifyPiece(blackKing, BLACK_KING);
    }

    @Test
    public void isBlackAndIsWhiteTestWithBlackPieces() {
        Piece blackPawn = Piece.createPiece(BLACK_PAWN);
        assertTrue(blackPawn.isBlack());
        assertFalse(blackPawn.isWhite());

        Piece blackKnight = Piece.createPiece(BLACK_KNIGHT);
        assertTrue(blackKnight.isBlack());
        assertFalse(blackKnight.isWhite());


        Piece blackRook = Piece.createPiece(BLACK_ROOK);
        assertTrue(blackRook.isBlack());
        assertFalse(blackRook.isWhite());

        Piece blackBishop = Piece.createPiece(BLACK_BISHOP);
        assertTrue(blackBishop.isBlack());
        assertFalse(blackBishop.isWhite());

        Piece blackQueen = Piece.createPiece(BLACK_QUEEN);
        assertTrue(blackQueen.isBlack());
        assertFalse(blackQueen.isWhite());

        Piece blackKing = Piece.createPiece(BLACK_KING);
        assertTrue(blackKing.isBlack());
        assertFalse(blackKing.isWhite());
    }

    @Test
    public void isBlackAndIsWhiteTestWithWhitePieces() {
        Piece whitePawn = Piece.createPiece(WHITE_PAWN);
        assertFalse(whitePawn.isBlack());
        assertTrue(whitePawn.isWhite());

        Piece whiteKnight = Piece.createPiece(WHITE_KNIGHT);
        assertFalse(whiteKnight.isBlack());
        assertTrue(whiteKnight.isWhite());


        Piece whiteRook = Piece.createPiece(WHITE_ROOK);
        assertFalse(whiteRook.isBlack());
        assertTrue(whiteRook.isWhite());

        Piece whiteBishop = Piece.createPiece(WHITE_BISHOP);
        assertFalse(whiteBishop.isBlack());
        assertTrue(whiteBishop.isWhite());

        Piece whiteQueen = Piece.createPiece(WHITE_QUEEN);
        assertFalse(whiteQueen.isBlack());
        assertTrue(whiteQueen.isWhite());

        Piece whiteKing = Piece.createPiece(WHITE_KING);
        assertFalse(whiteKing.isBlack());
        assertTrue(whiteKing.isWhite());
    }
}
