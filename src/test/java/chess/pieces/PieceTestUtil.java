package chess.pieces;

import static org.junit.jupiter.api.Assertions.assertEquals;

import chess.board.Board;

public class PieceTestUtil {
    public static void assertMove(Board board, Position src, Position dest, Piece piece) {
        assertEquals(PieceFactory.createBlankPiece(), board.findPiece(src));
        assertEquals(piece, board.findPiece(dest));
    }
}
