package chess.pieces;

import org.junit.jupiter.api.Test;

import static chess.pieces.PieceTypes.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PieceTest {
    private void verifyPiece(final Piece piece,final PieceTypes type){
        verifyPiece(piece,type.getColor(),type.getRepresentation(),type.getType());
    }
    private void verifyPiece(final Piece piece,final String color,final char representation,final String type){
        assertEquals(color,piece.getColor());
        assertEquals(representation,piece.getRepresentation());
        assertEquals(type,piece.getType());
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
        verifyPiece(whiteKnight,WHITE_KNIGHT);
        verifyPiece(blackKnight,BLACK_KNIGHT);
        verifyPiece(whiteRook,WHITE_ROOK);
        verifyPiece(blackRook,BLACK_ROOK);
        verifyPiece(whiteBishop,WHITE_BISHOP);
        verifyPiece(blackBishop,BLACK_BISHOP);
        verifyPiece(whiteQueen,WHITE_QUEEN);
        verifyPiece(blackQueen,BLACK_QUEEN);
        verifyPiece(whiteKing,WHITE_KING);
        verifyPiece(blackKing,BLACK_KING);
    }
}
