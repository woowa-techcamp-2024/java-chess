package chess.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PieceTest {

    @Test
    @DisplayName("생성자에 전달된 색의 기물이 생성되어야 한다")
    public void create_constructor() {
        verifyPiece(new King(Color.WHITE), King.class, Color.WHITE, "♔");
        verifyPiece(new Queen(Color.WHITE), Queen.class, Color.WHITE, "♕");
        verifyPiece(new Rook(Color.WHITE), Rook.class, Color.WHITE, "♖");
        verifyPiece(new Bishop(Color.WHITE), Bishop.class, Color.WHITE, "♗");
        verifyPiece(new Knight(Color.WHITE), Knight.class, Color.WHITE, "♘");
        verifyPiece(new Pawn(Color.WHITE), Pawn.class, Color.WHITE, "♙");
        verifyPiece(new King(Color.BLACK), King.class, Color.BLACK, "♚");
        verifyPiece(new Queen(Color.BLACK), Queen.class, Color.BLACK, "♛");
        verifyPiece(new Rook(Color.BLACK), Rook.class, Color.BLACK, "♜");
        verifyPiece(new Bishop(Color.BLACK), Bishop.class, Color.BLACK, "♝");
        verifyPiece(new Knight(Color.BLACK), Knight.class, Color.BLACK, "♞");
        verifyPiece(new Pawn(Color.BLACK), Pawn.class, Color.BLACK, "♟");
    }

    @Test
    @DisplayName("팩토리 메소드에 해당하는 기물이 생성되어야 한다")
    public void create_factory() {
        verifyPiece(Piece.createWhiteKing(), King.class, Color.WHITE, "♔");
        verifyPiece(Piece.createWhiteQueen(), Queen.class, Color.WHITE, "♕");
        verifyPiece(Piece.createWhiteRook(), Rook.class, Color.WHITE, "♖");
        verifyPiece(Piece.createWhiteBishop(), Bishop.class, Color.WHITE, "♗");
        verifyPiece(Piece.createWhiteKnight(), Knight.class, Color.WHITE, "♘");
        verifyPiece(Piece.createWhitePawn(), Pawn.class, Color.WHITE, "♙");
        verifyPiece(Piece.createBlackKing(), King.class, Color.BLACK, "♚");
        verifyPiece(Piece.createBlackQueen(), Queen.class, Color.BLACK, "♛");
        verifyPiece(Piece.createBlackRook(), Rook.class, Color.BLACK, "♜");
        verifyPiece(Piece.createBlackBishop(), Bishop.class, Color.BLACK, "♝");
        verifyPiece(Piece.createBlackKnight(), Knight.class, Color.BLACK, "♞");
        verifyPiece(Piece.createBlackPawn(), Pawn.class, Color.BLACK, "♟");
    }

    @Test
    @DisplayName("생성자에 색이 전달되지 않을 경우 흰색 폰이 생성되어야 한다")
    public void create_defaultConstructor() {
        verifyColor(new Pawn(), Color.WHITE);
    }

    private void verifyColor(final Piece piece, final Color color) {
        assertThat(piece.getColor()).isEqualTo(color);
    }

    private void verifyPiece(final Piece piece, final Class<? extends Piece> type, final Color color, final String representation) {
        assertThat(piece).isInstanceOf(type);
        assertThat(piece.getColor()).isEqualTo(color);
        assertThat(piece).hasToString(representation);
    }

    @Test
    @DisplayName("isBlack, isWhite 반환값이 getColor의 상태와 일치해야 한다")
    public void color() {
        Pawn white = Piece.createWhitePawn();
        assertThat(white.isWhite()).isTrue();
        assertThat(white.isBlack()).isFalse();
        Pawn black = Piece.createBlackPawn();
        assertThat(black.isWhite()).isFalse();
        assertThat(black.isBlack()).isTrue();
    }

}
