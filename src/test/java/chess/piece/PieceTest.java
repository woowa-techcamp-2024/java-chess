package chess.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PieceTest {

    @Test
    @DisplayName("폰이 생성되어야 한다")
    public void createPawn() {
        verifyPiece(Pawn.create(PieceColor.WHITE), PieceColor.WHITE, PieceRepresentation.PAWN_WHITE);
        verifyPiece(Pawn.create(PieceColor.BLACK), PieceColor.BLACK, PieceRepresentation.PAWN_BLACK);
    }

    @Test
    @DisplayName("퀸이 생성되어야 한다")
    public void createQueen() {
        verifyPiece(Queen.create(PieceColor.WHITE), PieceColor.WHITE, PieceRepresentation.QUEEN_WHITE);
        verifyPiece(Queen.create(PieceColor.BLACK), PieceColor.BLACK, PieceRepresentation.QUEEN_BLACK);
    }

    @Test
    @DisplayName("룩이 생성되어야 한다")
    public void createRook() {
        verifyPiece(Rook.create(PieceColor.WHITE), PieceColor.WHITE, PieceRepresentation.ROOK_WHITE);
        verifyPiece(Rook.create(PieceColor.BLACK), PieceColor.BLACK, PieceRepresentation.ROOK_BLACK);
    }

    @Test
    @DisplayName("킹이 생성되어야 한다")
    public void createKing() {
        verifyPiece(King.create(PieceColor.WHITE), PieceColor.WHITE, PieceRepresentation.KING_WHITE);
        verifyPiece(King.create(PieceColor.BLACK), PieceColor.BLACK, PieceRepresentation.KING_BLACK);
    }

    @Test
    @DisplayName("나이트이 생성되어야 한다")
    public void createKnight() {
        verifyPiece(Knight.create(PieceColor.WHITE), PieceColor.WHITE, PieceRepresentation.KNIGHT_WHITE);
        verifyPiece(Knight.create(PieceColor.BLACK), PieceColor.BLACK, PieceRepresentation.KNIGHT_BLACK);
    }

    @Test
    @DisplayName("비숍이 생성되어야 한다")
    public void createBishop() {
        verifyPiece(Bishop.create(PieceColor.WHITE), PieceColor.WHITE, PieceRepresentation.BISHOP_WHITE);
        verifyPiece(Bishop.create(PieceColor.BLACK), PieceColor.BLACK, PieceRepresentation.BISHOP_BLACK);
    }

    private void verifyPiece(final Piece piece, final PieceColor color, final PieceRepresentation representation) {
        assertThat(piece.getColor()).isEqualTo(color);
        assertThat(PieceRepresentation.getPieceRepresentation(piece.getColor(), piece.getType()))
                .isEqualTo(representation.getPieceRepresentation());
    }
}