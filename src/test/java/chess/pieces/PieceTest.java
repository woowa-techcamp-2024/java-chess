package chess.pieces;

import org.junit.jupiter.api.Test;

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
        Piece white = Piece.createPiece(PieceTypes.WHITE_PAWN);
        Piece black = Piece.createPiece(PieceTypes.BLACK_PAWN);
        verifyPiece(white, PieceTypes.WHITE_PAWN);
        verifyPiece(black, PieceTypes.BLACK_PAWN);
    }
}
