package org.example.chess.pieces;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PieceTest {
    private static final Piece.Color white = Piece.Color.WHITE;
    private static final Piece.Color black = Piece.Color.BLACK;


    @Test
    public void createWhitePawn() throws Exception {
        Piece piece = Pawn.of(Piece.Color.WHITE);
        assertTrue(piece.isWhite());
    }

    @Test
    public void create_piece() {
        verifyPiece(Pawn.of(Piece.Color.WHITE), "p");
        verifyPiece(Pawn.of(Piece.Color.BLACK), "P");

        verifyPiece(Knight.of(Piece.Color.WHITE), "n");
        verifyPiece(Knight.of(Piece.Color.BLACK), "N");

        verifyPiece(Rook.of(Piece.Color.WHITE), "r");
        verifyPiece(Rook.of(Piece.Color.BLACK), "R");

        verifyPiece(Bishop.of(Piece.Color.WHITE), "b");
        verifyPiece(Bishop.of(Piece.Color.BLACK), "B");

        verifyPiece(Queen.of(Piece.Color.WHITE), "q");
        verifyPiece(Queen.of(Piece.Color.BLACK), "Q");

        verifyPiece(King.of(Piece.Color.WHITE), "k");
        verifyPiece(King.of(Piece.Color.BLACK), "K");
    }

    private void verifyPiece(final Piece piece, String representation) {
        assertEquals(representation, piece.getRepresentation());
    }
}