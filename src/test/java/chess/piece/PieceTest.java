package chess.piece;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PieceTest {
    @Test
    public void create_piece() {
        verifyPiece(Pawn.createWhitePawn(), Piece.WHITE_COLOR, Pawn.WHITE_REPRESENTATION);
        verifyPiece(Pawn.createBlackPawn(), Piece.BLACK_COLOR, Pawn.BLACK_REPRESENTATION);

        verifyPiece(Rook.createWhiteRook(), Piece.WHITE_COLOR, Rook.WHITE_REPRESENTATION);
        verifyPiece(Rook.createBlackRook(), Piece.BLACK_COLOR, Rook.BLACK_REPRESENTATION);

        verifyPiece(Knight.createWhiteKnight(), Piece.WHITE_COLOR, Knight.WHITE_REPRESENTATION);
        verifyPiece(Knight.createBlackKnight(), Piece.BLACK_COLOR, Knight.BLACK_REPRESENTATION);

        verifyPiece(Bishop.createWhiteBishop(), Piece.WHITE_COLOR, Bishop.WHITE_REPRESENTATION);
        verifyPiece(Bishop.createBlackBishop(), Piece.BLACK_COLOR, Bishop.BLACK_REPRESENTATION);

        verifyPiece(Queen.createWhiteQueen(), Piece.WHITE_COLOR, Queen.WHITE_REPRESENTATION);
        verifyPiece(Queen.createBlackQueen(), Piece.BLACK_COLOR, Queen.BLACK_REPRESENTATION);

        verifyPiece(King.createWhiteKing(), Piece.WHITE_COLOR, King.WHITE_REPRESENTATION);
        verifyPiece(King.createBlackKing(), Piece.BLACK_COLOR, King.BLACK_REPRESENTATION);
    }

    private void verifyPiece(final Piece piece, final String color, final char representation) {
        assertEquals(color, piece.getColor());
        assertEquals(representation, piece.getRepresentation());
    }

    @Test
    public void is_white() {
        assertTrue(Pawn.createWhitePawn().isWhite());
        assertTrue(Rook.createWhiteRook().isWhite());
        assertTrue(Knight.createWhiteKnight().isWhite());
        assertTrue(Bishop.createWhiteBishop().isWhite());
        assertTrue(Queen.createWhiteQueen().isWhite());
        assertTrue(King.createWhiteKing().isWhite());
    }

    @Test
    public void is_black() {
        assertTrue(Pawn.createBlackPawn().isBlack());
        assertTrue(Rook.createBlackRook().isBlack());
        assertTrue(Knight.createBlackKnight().isBlack());
        assertTrue(Bishop.createBlackBishop().isBlack());
        assertTrue(Queen.createBlackQueen().isBlack());
        assertTrue(King.createBlackKing().isBlack());
    }
}