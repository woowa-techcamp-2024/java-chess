package chess.pieces;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PieceTest {

    @Test
    public void 피스_생성() {
        verifyPiece(Piece.createWhitePawn(), Piece.Color.WHITE, Piece.Representation.WHITE_PAWN.getSymbol());
        verifyPiece(Piece.createBlackPawn(), Piece.Color.BLACK, Piece.Representation.BLACK_PAWN.getSymbol());
        verifyPiece(Piece.createWhiteKnight(), Piece.Color.WHITE, Piece.Representation.WHITE_KNIGHT.getSymbol());
        verifyPiece(Piece.createBlackKnight(), Piece.Color.BLACK, Piece.Representation.BLACK_KNIGHT.getSymbol());
        verifyPiece(Piece.createWhiteBishop(), Piece.Color.WHITE, Piece.Representation.WHITE_BISHOP.getSymbol());
        verifyPiece(Piece.createBlackBishop(), Piece.Color.BLACK, Piece.Representation.BLACK_BISHOP.getSymbol());
        verifyPiece(Piece.createWhiteRook(), Piece.Color.WHITE, Piece.Representation.WHITE_ROOK.getSymbol());
        verifyPiece(Piece.createBlackRook(), Piece.Color.BLACK, Piece.Representation.BLACK_ROOK.getSymbol());
        verifyPiece(Piece.createWhiteQueen(), Piece.Color.WHITE, Piece.Representation.WHITE_QUEEN.getSymbol());
        verifyPiece(Piece.createBlackQueen(), Piece.Color.BLACK, Piece.Representation.BLACK_QUEEN.getSymbol());
        verifyPiece(Piece.createWhiteKing(), Piece.Color.WHITE, Piece.Representation.WHITE_KING.getSymbol());
        verifyPiece(Piece.createBlackKing(), Piece.Color.BLACK, Piece.Representation.BLACK_KING.getSymbol());
    }

    private void verifyPiece(Piece piece, Piece.Color color, char symbol) {
        assertEquals(color, piece.getColor());
        assertEquals(symbol, piece.getRepresentation().getSymbol());
    }

    @Test
    public void 색_확인() {
        Piece whitePawn = Piece.createWhitePawn();
        Piece blackPawn = Piece.createBlackPawn();
        assertThat(whitePawn.isWhite()).isTrue();
        assertThat(blackPawn.isBlack()).isTrue();
        assertThat(whitePawn.isBlack()).isFalse();
        assertThat(blackPawn.isWhite()).isFalse();
    }
}
