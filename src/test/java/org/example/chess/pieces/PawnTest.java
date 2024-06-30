package org.example.chess.pieces;

import org.example.chess.Board;
import org.example.chess.pieces.global.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PawnTest {
    private Board board;
    @Test
    public void pawn은_위로_한칸_움직일수있다() throws Exception {
        Position sourcePosition = Position.of("b2");
        board.setPiece(sourcePosition, Pawn.of(Piece.Color.WHITE));

        Position targetPosition = Position.of("b3");
        board.moveTo(sourcePosition, targetPosition);

        board.showBoard();
        assertEquals(NoPiece.of(), board.findPiece(sourcePosition));
        assertEquals(Pawn.of(Piece.Color.WHITE), board.findPiece(targetPosition));
    }

    @Test
    public void pawn은_첫_이동에_대해서는_위로_두칸_움직일수있다() throws Exception {
        Position sourcePosition = Position.of("b2");
        board.setPiece(sourcePosition, Pawn.of(Piece.Color.WHITE));

        Position targetPosition = Position.of("b4");
        board.moveTo(sourcePosition, targetPosition);

        board.showBoard();
        assertEquals(NoPiece.of(), board.findPiece(sourcePosition));
        assertEquals(Pawn.of(Piece.Color.WHITE), board.findPiece(targetPosition));
    }

    @BeforeEach()
    void setup() {
        board = new Board();
        board.initializeEmpty();
    }
}
