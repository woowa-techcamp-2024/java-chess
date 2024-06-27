package javachess.chess.piece;

import javachess.chess.Board;
import javachess.chess.BoardContext;
import javachess.chess.Offset;
import javachess.chess.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static javachess.chess.BoardUtils.createBoard;
import static javachess.chess.BoardUtils.createBoardContext;
import static javachess.chess.piece.PieceUtils.*;
import static org.assertj.core.api.Assertions.assertThat;

public class QueenTest {

    @Test
    @DisplayName("팩토리 메소드에 해당하는 퀸이 생성되어야 한다")
    public void factory() {
        assertIsBlack(Queen.createBlack());
        assertIsWhite(Queen.createWhite());
    }

    @Test
    @DisplayName("생성자에 전달된 색의 퀸이 생성되어야 한다")
    public void create() {
        assertIsBlack(new Queen(Piece.Color.BLACK));
        assertIsWhite(new Queen(Piece.Color.WHITE));
    }

    @Test
    @DisplayName("이동하지 않은 상태의 퀸이 생성되어야 한다")
    public void moved() {
        assertNotMoved(new Queen(Piece.Color.BLACK));
    }

    @Test
    @DisplayName("퀸을 이동한 경우 이동 여부가 참이어야 한다")
    public void move() {
        Queen pawn = new Queen(Piece.Color.BLACK);

        pawn.setMoved();

        assertIsMoved(pawn);
    }

    @DisplayName("퀸이 십자가 및 대각선 이동을 지원해야 한다")
    @ParameterizedTest
    @ValueSource(strings = {"a3", "b3", "d3", "c1", "c2", "c4", "a1", "b2", "d4", "e5", "f6", "g7", "h8", "b4", "a5", "d2", "e1"})
    public void canMove(String toStr) {
        Board board = createBoard("""
                ........
                ........
                ........
                ........
                ........
                ..Q.....
                ........
                ........
                """);
        Position from = Position.of("c3");
        Position to = Position.of(toStr);
        Piece piece = board.get(from);

        Offset offset = Offset.between(from, to);
        BoardContext context = createBoardContext(board, from);
        boolean canMove = piece.canMove(offset, context);

        assertThat(piece).isInstanceOf(Queen.class);
        assertThat(canMove).isTrue();
    }

    @DisplayName("퀸이 십자가 또는 대각선이 아닌 이동을 지원하지 않아야 한다")
    @ParameterizedTest
    @ValueSource(strings = {"a6", "a8", "b8", "d1", "e7", "f4"})
    public void canNotMove(String toStr) {
        Board board = createBoard("""
                ........
                ........
                ........
                ........
                ........
                ..q.....
                ........
                ........
                """);
        Position from = Position.of("c3");
        Position to = Position.of(toStr);
        Piece piece = board.get(from);

        Offset offset = Offset.between(from, to);
        BoardContext context = createBoardContext(board, from);
        boolean canMove = piece.canMove(offset, context);

        assertThat(piece).isInstanceOf(Queen.class);
        assertThat(canMove).isFalse();
    }

    @DisplayName("퀸의 중간 이동 경로에 기물이 있는 경우 이동을 지원하지 않아야 한다")
    @ParameterizedTest
    @ValueSource(strings = {"a3", "c1", "f6", "g3", "g7", "h3", "h8"})
    public void canNotMoveBlocked(String toStr) {
        Board board = createBoard("""
                ........
                ........
                ........
                ....R...
                ........
                .pQ..Q..
                ..k.....
                ........
                """);
        Position from = Position.of("c3");
        Position to = Position.of(toStr);
        Piece piece = board.get(from);

        Offset offset = Offset.between(from, to);
        BoardContext context = createBoardContext(board, from);
        boolean canMove = piece.canMove(offset, context);

        assertThat(piece).isInstanceOf(Queen.class);
        assertThat(canMove).isFalse();
    }
}
