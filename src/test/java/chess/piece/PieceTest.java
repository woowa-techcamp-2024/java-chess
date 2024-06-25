package chess.piece;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PieceTest {
    @Test
    public void create_piece() {
        verifyPiece(Pawn.createWhite(), Piece.Color.WHITE, Pawn.WHITE_REPRESENTATION);
        verifyPiece(Pawn.createBlack(), Piece.Color.BLACK, Pawn.BLACK_REPRESENTATION);

        verifyPiece(Rook.createWhite(), Piece.Color.WHITE, Rook.WHITE_REPRESENTATION);
        verifyPiece(Rook.createBlack(), Piece.Color.BLACK, Rook.BLACK_REPRESENTATION);

        verifyPiece(Knight.createWhiteKnight(), Piece.Color.WHITE, Knight.WHITE_REPRESENTATION);
        verifyPiece(Knight.createBlackKnight(), Piece.Color.BLACK, Knight.BLACK_REPRESENTATION);

        verifyPiece(Bishop.createWhite(), Piece.Color.WHITE, Bishop.WHITE_REPRESENTATION);
        verifyPiece(Bishop.createBlack(), Piece.Color.BLACK, Bishop.BLACK_REPRESENTATION);

        verifyPiece(Queen.createWhite(), Piece.Color.WHITE, Queen.WHITE_REPRESENTATION);
        verifyPiece(Queen.createBlack(), Piece.Color.BLACK, Queen.BLACK_REPRESENTATION);

        verifyPiece(King.createWhite(), Piece.Color.WHITE, King.WHITE_REPRESENTATION);
        verifyPiece(King.createBlack(), Piece.Color.BLACK, King.BLACK_REPRESENTATION);
    }

    private void verifyPiece(final Piece piece, final Piece.Color color, final char representation) {
        System.out.println(piece.getClass());
        assertEquals(color, piece.getColor());
        assertEquals(representation, piece.getRepresentation());
    }

    @Test
    public void is_white() {
        assertTrue(Pawn.createWhite().isWhite());
        assertTrue(Rook.createWhite().isWhite());
        assertTrue(Knight.createWhiteKnight().isWhite());
        assertTrue(Bishop.createWhite().isWhite());
        assertTrue(Queen.createWhite().isWhite());
        assertTrue(King.createWhite().isWhite());
    }

    @Test
    public void is_black() {
        assertTrue(Pawn.createBlack().isBlack());
        assertTrue(Rook.createBlack().isBlack());
        assertTrue(Knight.createBlackKnight().isBlack());
        assertTrue(Bishop.createBlack().isBlack());
        assertTrue(Queen.createBlack().isBlack());
        assertTrue(King.createBlack().isBlack());
    }
}