package javachess.chess;

import javachess.chess.piece.Pawn;
import javachess.chess.piece.Piece;
import javachess.chess.piece.Rook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static javachess.chess.BoardUtils.createBoard;
import static org.assertj.core.api.Assertions.assertThat;

public class BoardTest {

    @Test
    @DisplayName("보드를 생성하고 기물이 추가되어야 한다.")
    public void create() {
        Board board = new Board();

        Piece white = Pawn.createWhite();
        Position whitePosition = Position.of("b1");
        board.set(whitePosition, white);
        assertThat(board.get(whitePosition)).isEqualTo(white);

        Piece black = Pawn.createBlack();
        Position blackPosition = Position.of("a3");
        board.set(blackPosition, black);
        assertThat(board.get(blackPosition)).isEqualTo(black);
    }

    @Test
    @DisplayName("보드의 toString 결과에 유니코드 체스말 형식으로 표시되어야 한다.")
    public void string() {
        Board board = new Board();
        board.set(Position.of("a2"), Pawn.createWhite());
        board.set(Position.of("b7"), Pawn.createBlack());
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
    @DisplayName("체스판의 점수 결과를 반환해야 한다")
    public void value() {
        Board board = createBoard("""
                .KR.....
                P.PB....
                .P..Q...
                ........
                .....nq.
                .....p.p
                .....pp.
                ....rk..
                """);
        assertThat(board.value(Piece.Color.BLACK)).isEqualTo(20.0);
        assertThat(board.value(Piece.Color.WHITE)).isEqualTo(20.0);
    }

    @Test
    @DisplayName("체스판의 기물이 이동되어야 한다")
    public void move() {
        Board board = new Board();
        board.initialize();
        Position from = Position.of("a2");
        Position to = Position.of("a3");
        Piece pieceBeforeMove = board.get(from);

        board.move(from, to);

        assertThat(board.get(from)).isNull();
        assertThat(board.get(to)).isEqualTo(pieceBeforeMove);
    }

}
