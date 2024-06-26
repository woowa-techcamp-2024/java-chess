package chess.pieces;

import org.junit.jupiter.api.Test;

import static chess.pieces.PieceTypes.*;
import static org.junit.jupiter.api.Assertions.*;

public class ChessPieceTest {
    private void verifyPiece(final ChessPiece chessPiece, final PieceTypes type) {
        verifyPiece(chessPiece, type.getColor(), type.getRepresentation(), type.getType());
    }

    private void verifyPiece(final ChessPiece chessPiece, final Color color, final char representation, final Type type) {
        assertEquals(color, chessPiece.getColor());
        assertEquals(representation, chessPiece.getRepresentation());
        assertEquals(type, chessPiece.getType());
    }

    @Test
    public void create() {
        ChessPiece whitePawn = PieceFactory.createPiece(WHITE_PAWN);
        ChessPiece blackPawn = PieceFactory.createPiece(BLACK_PAWN);
        ChessPiece whiteKnight = PieceFactory.createPiece(WHITE_KNIGHT);
        ChessPiece blackKnight = PieceFactory.createPiece(BLACK_KNIGHT);
        ChessPiece whiteRook = PieceFactory.createPiece(WHITE_ROOK);
        ChessPiece blackRook = PieceFactory.createPiece(BLACK_ROOK);
        ChessPiece whiteBishop = PieceFactory.createPiece(WHITE_BISHOP);
        ChessPiece blackBishop = PieceFactory.createPiece(BLACK_BISHOP);
        ChessPiece whiteQueen = PieceFactory.createPiece(WHITE_QUEEN);
        ChessPiece blackQueen = PieceFactory.createPiece(BLACK_QUEEN);
        ChessPiece whiteKing = PieceFactory.createPiece(WHITE_KING);
        ChessPiece blackKing = PieceFactory.createPiece(BLACK_KING);

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
        ChessPiece blackPawn = PieceFactory.createPiece(BLACK_PAWN);
        assertTrue(blackPawn.isBlack());
        assertFalse(blackPawn.isWhite());

        ChessPiece blackKnight = PieceFactory.createPiece(BLACK_KNIGHT);
        assertTrue(blackKnight.isBlack());
        assertFalse(blackKnight.isWhite());


        ChessPiece blackRook = PieceFactory.createPiece(BLACK_ROOK);
        assertTrue(blackRook.isBlack());
        assertFalse(blackRook.isWhite());

        ChessPiece blackBishop = PieceFactory.createPiece(BLACK_BISHOP);
        assertTrue(blackBishop.isBlack());
        assertFalse(blackBishop.isWhite());

        ChessPiece blackQueen = PieceFactory.createPiece(BLACK_QUEEN);
        assertTrue(blackQueen.isBlack());
        assertFalse(blackQueen.isWhite());

        ChessPiece blackKing = PieceFactory.createPiece(BLACK_KING);
        assertTrue(blackKing.isBlack());
        assertFalse(blackKing.isWhite());
    }

    @Test
    public void isBlackAndIsWhiteTestWithWhitePieces() {
        ChessPiece whitePawn = PieceFactory.createPiece(WHITE_PAWN);
        assertFalse(whitePawn.isBlack());
        assertTrue(whitePawn.isWhite());

        ChessPiece whiteKnight = PieceFactory.createPiece(WHITE_KNIGHT);
        assertFalse(whiteKnight.isBlack());
        assertTrue(whiteKnight.isWhite());


        ChessPiece whiteRook = PieceFactory.createPiece(WHITE_ROOK);
        assertFalse(whiteRook.isBlack());
        assertTrue(whiteRook.isWhite());

        ChessPiece whiteBishop = PieceFactory.createPiece(WHITE_BISHOP);
        assertFalse(whiteBishop.isBlack());
        assertTrue(whiteBishop.isWhite());

        ChessPiece whiteQueen = PieceFactory.createPiece(WHITE_QUEEN);
        assertFalse(whiteQueen.isBlack());
        assertTrue(whiteQueen.isWhite());

        ChessPiece whiteKing = PieceFactory.createPiece(WHITE_KING);
        assertFalse(whiteKing.isBlack());
        assertTrue(whiteKing.isWhite());
    }

    @Test
    public void createBlank(){
        ChessPiece blank = PieceFactory.createPiece(NO_PIECE);

        assertEquals(Type.NO_PIECE,blank.getType());
        assertFalse(blank.isBlack());
        assertFalse(blank.isWhite());
    }
}
