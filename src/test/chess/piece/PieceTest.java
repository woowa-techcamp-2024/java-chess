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
        verifyPiece(King.createWhite(), King.class, Piece.Color.WHITE, "♔");
        verifyPiece(Queen.createWhite(), Queen.class, Piece.Color.WHITE, "♕");
        verifyPiece(Rook.createWhite(), Rook.class, Piece.Color.WHITE, "♖");
        verifyPiece(Bishop.createWhite(), Bishop.class, Piece.Color.WHITE, "♗");
        verifyPiece(Knight.createWhite(), Knight.class, Piece.Color.WHITE, "♘");
        verifyPiece(Pawn.createWhite(), Pawn.class, Piece.Color.WHITE, "♙");
        verifyPiece(King.createBlack(), King.class, Piece.Color.BLACK, "♚");
        verifyPiece(Queen.createBlack(), Queen.class, Piece.Color.BLACK, "♛");
        verifyPiece(Rook.createBlack(), Rook.class, Piece.Color.BLACK, "♜");
        verifyPiece(Bishop.createBlack(), Bishop.class, Piece.Color.BLACK, "♝");
        verifyPiece(Knight.createBlack(), Knight.class, Piece.Color.BLACK, "♞");
        verifyPiece(Pawn.createBlack(), Pawn.class, Piece.Color.BLACK, "♟");
    }

    private void verifyPiece(final Piece piece, final Class<? extends Piece> type, final Piece.Color color, final String representation) {
        assertThat(piece).isInstanceOf(type);
        assertThat(piece.isColor(color)).isTrue();
        assertThat(piece).hasToString(representation);
    }

    @Test
    @DisplayName("isBlack, isWhite 반환값이 getColor의 상태와 일치해야 한다")
    public void color() {
        Piece white = Pawn.createWhite();
        assertThat(white.isWhite()).isTrue();
        assertThat(white.isBlack()).isFalse();
        Piece black = Pawn.createBlack();
        assertThat(black.isWhite()).isFalse();
        assertThat(black.isBlack()).isTrue();
    }

}
