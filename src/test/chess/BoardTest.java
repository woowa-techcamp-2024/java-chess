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
        board.set(1, 2, white);
        assertThat(board.get(1, 2)).isEqualTo(white);
        assertThat(board.findPawns()).hasSize(1);
        assertThat(board.findPawns().get(0)).isEqualTo(white);

        Piece black = Piece.createBlack(Piece.Type.PAWN);
        board.set(0, 3, black);
        assertThat(board.get(0, 3)).isEqualTo(black);
        assertThat(board.findPawns()).hasSize(2);
        assertThat(board.findPawns().get(0)).isEqualTo(black);
    }

    @Test
    @DisplayName("보드의 toString 결과에 유니코드 체스말 형식으로 표시되어야 한다.")
    public void string() {
        Board board = new Board();
        board.set(1, 0, Piece.createWhite(Piece.Type.PAWN));
        board.set(6, 1, Piece.createBlack(Piece.Type.PAWN));
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

}
