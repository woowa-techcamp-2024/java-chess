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
        verifyPiece(PieceFactory.createWhitePawn(Position.NO_POSITION), PieceFactory.createBlackPawn(Position.NO_POSITION), Type.PAWN);
        verifyPiece(PieceFactory.createWhiteRook(Position.NO_POSITION), PieceFactory.createBlackRook(Position.NO_POSITION), Type.ROOK);
        verifyPiece(PieceFactory.createWhiteKnight(Position.NO_POSITION), PieceFactory.createBlackKnight(Position.NO_POSITION), Type.KNIGHT);
        verifyPiece(PieceFactory.createWhiteBishop(Position.NO_POSITION), PieceFactory.createBlackBishop(Position.NO_POSITION), Type.BISHOP);
        verifyPiece(PieceFactory.createWhiteQueen(Position.NO_POSITION), PieceFactory.createBlackQueen(Position.NO_POSITION), Type.QUEEN);
        verifyPiece(PieceFactory.createWhiteKing(Position.NO_POSITION), PieceFactory.createBlackKing(Position.NO_POSITION), Type.KING);

        Piece blank = PieceFactory.createBlank(Position.NO_POSITION);
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
