package chess.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PieceTest {

    @Test
    @DisplayName("생성자에 전달된 색의 기물이 생성되어야 한다")
    public void create_constructor() {
        verifyPiece(new King(Piece.Color.WHITE), King.class, Piece.Color.WHITE, "♔");
        verifyPiece(new Queen(Piece.Color.WHITE), Queen.class, Piece.Color.WHITE, "♕");
        verifyPiece(new Rook(Piece.Color.WHITE), Rook.class, Piece.Color.WHITE, "♖");
        verifyPiece(new Bishop(Piece.Color.WHITE), Bishop.class, Piece.Color.WHITE, "♗");
        verifyPiece(new Knight(Piece.Color.WHITE), Knight.class, Piece.Color.WHITE, "♘");
        verifyPiece(new Pawn(Piece.Color.WHITE), Pawn.class, Piece.Color.WHITE, "♙");
        verifyPiece(new King(Piece.Color.BLACK), King.class, Piece.Color.BLACK, "♚");
        verifyPiece(new Queen(Piece.Color.BLACK), Queen.class, Piece.Color.BLACK, "♛");
        verifyPiece(new Rook(Piece.Color.BLACK), Rook.class, Piece.Color.BLACK, "♜");
        verifyPiece(new Bishop(Piece.Color.BLACK), Bishop.class, Piece.Color.BLACK, "♝");
        verifyPiece(new Knight(Piece.Color.BLACK), Knight.class, Piece.Color.BLACK, "♞");
        verifyPiece(new Pawn(Piece.Color.BLACK), Pawn.class, Piece.Color.BLACK, "♟");
    }

    @Test
    @DisplayName("팩토리 메소드에 해당하는 기물이 생성되어야 한다")
    public void create_factory() {
        verifyPiece(Piece.createWhite(Piece.Type.KING), King.class, Piece.Color.WHITE, "♔");
        verifyPiece(Piece.createWhite(Piece.Type.QUEEN), Queen.class, Piece.Color.WHITE, "♕");
        verifyPiece(Piece.createWhite(Piece.Type.ROOK), Rook.class, Piece.Color.WHITE, "♖");
        verifyPiece(Piece.createWhite(Piece.Type.BISHOP), Bishop.class, Piece.Color.WHITE, "♗");
        verifyPiece(Piece.createWhite(Piece.Type.KNIGHT), Knight.class, Piece.Color.WHITE, "♘");
        verifyPiece(Piece.createWhite(Piece.Type.PAWN), Pawn.class, Piece.Color.WHITE, "♙");
        verifyPiece(Piece.createBlack(Piece.Type.KING), King.class, Piece.Color.BLACK, "♚");
        verifyPiece(Piece.createBlack(Piece.Type.QUEEN), Queen.class, Piece.Color.BLACK, "♛");
        verifyPiece(Piece.createBlack(Piece.Type.ROOK), Rook.class, Piece.Color.BLACK, "♜");
        verifyPiece(Piece.createBlack(Piece.Type.BISHOP), Bishop.class, Piece.Color.BLACK, "♝");
        verifyPiece(Piece.createBlack(Piece.Type.KNIGHT), Knight.class, Piece.Color.BLACK, "♞");
        verifyPiece(Piece.createBlack(Piece.Type.PAWN), Pawn.class, Piece.Color.BLACK, "♟");
    }

    @Test
    @DisplayName("생성자에 색이 전달되지 않을 경우 흰색 폰이 생성되어야 한다")
    public void create_defaultConstructor() {
        verifyColor(new Pawn(), Piece.Color.WHITE);
    }

    private void verifyColor(final Piece piece, final Piece.Color color) {
        assertThat(piece.getColor()).isEqualTo(color);
    }

    private void verifyPiece(final Piece piece, final Class<? extends Piece> type, final Piece.Color color, final String representation) {
        assertThat(piece).isInstanceOf(type);
        assertThat(piece.getColor()).isEqualTo(color);
        assertThat(piece).hasToString(representation);
    }

    @Test
    @DisplayName("isBlack, isWhite 반환값이 getColor의 상태와 일치해야 한다")
    public void color() {
        Piece white = Piece.createWhite(Piece.Type.PAWN);
        assertThat(white.isWhite()).isTrue();
        assertThat(white.isBlack()).isFalse();
        Piece black = Piece.createBlack(Piece.Type.PAWN);
        assertThat(black.isWhite()).isFalse();
        assertThat(black.isBlack()).isTrue();
    }

}
