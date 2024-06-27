package chess.pieces;

import chess.board.Position;
import chess.pieces.type.Color;
import chess.pieces.type.Type;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class PieceTest {
    @Test
    @DisplayName("모든 종류의 기물이 생성되어야 한다")
    public void create_piece() {
        verifyPiece(Piece.create(Type.PAWN, Color.WHITE, Position.NO_POSITION), Piece.create(Type.PAWN, Color.BLACK, Position.NO_POSITION), Type.PAWN);
        verifyPiece(Piece.create(Type.KNIGHT, Color.WHITE, Position.NO_POSITION), Piece.create(Type.KNIGHT, Color.BLACK, Position.NO_POSITION), Type.KNIGHT);
        verifyPiece(Piece.create(Type.ROOK, Color.WHITE, Position.NO_POSITION), Piece.create(Type.ROOK, Color.BLACK, Position.NO_POSITION), Type.ROOK);
        verifyPiece(Piece.create(Type.BISHOP, Color.WHITE, Position.NO_POSITION), Piece.create(Type.BISHOP, Color.BLACK, Position.NO_POSITION), Type.BISHOP);
        verifyPiece(Piece.create(Type.QUEEN, Color.WHITE, Position.NO_POSITION), Piece.create(Type.QUEEN, Color.BLACK, Position.NO_POSITION), Type.QUEEN);
        verifyPiece(Piece.create(Type.KING, Color.WHITE, Position.NO_POSITION), Piece.create(Type.KING, Color.BLACK, Position.NO_POSITION), Type.KING);

        Piece blank = Piece.create(Type.NO_PIECE, Color.NOCOLOR, Position.NO_POSITION);
        assertFalse(blank.getColor().isWhite());
        assertFalse(blank.getColor().isBlack());
        assertEquals(Type.NO_PIECE, blank.getType());
    }

    private void verifyPiece(final Piece whitePiece, final Piece blackPiece, final Type type) {
        assertTrue(whitePiece.getColor().isWhite());
        assertEquals(type, whitePiece.getType());

        assertTrue(blackPiece.getColor().isBlack());
        assertEquals(type, blackPiece.getType());
    }
}
