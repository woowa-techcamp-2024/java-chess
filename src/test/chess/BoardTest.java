package chess;

import chess.piece.Piece;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardTest {

    @Test
    @DisplayName("보드를 생성하고 기물이 추가되어야 한다.")
    public void create() {
        Board board = new Board();

        Piece white = Piece.createWhite(Piece.Type.PAWN);
        board.set("b1", white);
        assertThat(board.get("b1")).isEqualTo(white);
        assertThat(board.findPawns()).hasSize(1);
        assertThat(board.findPawns().get(0)).isEqualTo(white);

        Piece black = Piece.createBlack(Piece.Type.PAWN);
        board.set("a2", black);
        assertThat(board.get("a2")).isEqualTo(black);
        assertThat(board.findPawns()).hasSize(2);
        assertThat(board.findPawns().get(1)).isEqualTo(black);
    }

    @Test
    @DisplayName("보드의 toString 결과에 유니코드 체스말 형식으로 표시되어야 한다.")
    public void string() {
        Board board = new Board();
        board.set("a2", Piece.createWhite(Piece.Type.PAWN));
        board.set("b7", Piece.createBlack(Piece.Type.PAWN));
        assertThat(board).hasToString("""
                ........
                .♟......
                ........
                ........
                ........
                ........
                ♙.......
                ........
                """);
    }

    @Test
    @DisplayName("보드의 초기화 후 상태가 체스 규칙과 일치해야 한다.")
    public void initializeBoard() {
        Board board = new Board();
        board.initialize();
        assertThat(board).hasToString("""
                ♜♞♝♛♚♝♞♜
                ♟♟♟♟♟♟♟♟
                ........
                ........
                ........
                ........
                ♙♙♙♙♙♙♙♙
                ♖♘♗♕♔♗♘♖
                """);
    }

    @Test
    @DisplayName("기물과 색에 해당하는 기물의 개수를 반환해야 한다")
    public void count() {
        Board board = new Board();
        board.initialize();
        assertThat(board.countPiece(Piece.Color.BLACK, Piece.Type.PAWN)).isEqualTo(8);
        assertThat(board.countPiece(Piece.Color.WHITE, Piece.Type.ROOK)).isEqualTo(2);
    }

}
