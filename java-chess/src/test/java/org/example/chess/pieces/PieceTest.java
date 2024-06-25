package org.example.chess.pieces;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PieceTest {
    private static final Piece.Color white = Piece.Color.WHITE;
    private static final Piece.Color black = Piece.Color.BLACK;


    @Test
    public void createWhitePawn() throws Exception {
        Piece piece = Piece.createWhitePawn();
        assertTrue(piece.isWhite());
    }

    @Test
    public void create_piece() {
        verifyPiece(Piece.createWhitePawn(), "p");
        verifyPiece(Piece.createBlackPawn(), "P");

        verifyPiece(Piece.createWhiteKnight(), "n");
        verifyPiece(Piece.createBlackKnight(), "N");

        verifyPiece(Piece.createWhiteRook(), "r");
        verifyPiece(Piece.createBlackRook(), "R");

        verifyPiece(Piece.createWhiteBishop(), "b");
        verifyPiece(Piece.createBlackBishop(), "B");

        verifyPiece(Piece.createWhiteQueen(), "q");
        verifyPiece(Piece.createBlackQueen(), "Q");

        verifyPiece(Piece.createWhiteKing(), "k");
        verifyPiece(Piece.createBlackKing(), "K");
    }

    private void verifyPiece(final Piece piece, String representation) {
        assertEquals(representation, piece.getRepresentation());
    }
}