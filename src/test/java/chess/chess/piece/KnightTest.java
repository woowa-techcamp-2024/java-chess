package chess.piece;

import chess.Board;
import chess.BoardContext;
import chess.Offset;
import chess.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static chess.BoardUtils.createBoard;
import static chess.BoardUtils.createBoardContext;
import static chess.piece.PieceUtils.*;
import static org.assertj.core.api.Assertions.assertThat;

public class KnightTest {

    @Test
    @DisplayName("팩토리 메소드에 해당하는 나이트가 생성되어야 한다")
    public void factory() {
        assertIsBlack(Knight.createBlack());
        assertIsWhite(Knight.createWhite());
    }

    @Test
    @DisplayName("생성자에 전달된 색의 나이트가 생성되어야 한다")
    public void create() {
        assertIsBlack(new Knight(Piece.Color.BLACK));
        assertIsWhite(new Knight(Piece.Color.WHITE));
    }

    @Test
    @DisplayName("이동하지 않은 상태의 나이트가 생성되어야 한다")
    public void moved() {
        assertNotMoved(new Knight(Piece.Color.BLACK));
    }

    @Test
    @DisplayName("나이트를 이동한 경우 이동 여부가 참이어야 한다")
    public void move() {
        Knight pawn = new Knight(Piece.Color.BLACK);

        pawn.setMoved();

        assertIsMoved(pawn);
    }

    @DisplayName("나이트가 한 방향으로 두 칸 후 이동 방향에 수직인 방향으로 한 칸 이동을 지원해야 한다")
    @ParameterizedTest
    @ValueSource(strings = {"a2", "a4", "b1", "b5", "d1", "d5", "e2", "e4"})
    public void canMove(String toStr) {
        Board board = createBoard("""
                ........
                ........
                ........
                ........
                ........
                ..n.....
                ........
                ........
                """);
        Position from = Position.of("c3");
        Position to = Position.of(toStr);
        Piece piece = board.get(from);

        Offset offset = Offset.between(from, to);
        BoardContext context = createBoardContext(board, from);
        boolean canMove = piece.canMove(offset, context);

        assertThat(piece).isInstanceOf(Knight.class);
        assertThat(canMove).isTrue();
    }

    @DisplayName("나이트가 한 방향으로 두 칸 후 이동 방향에 수직인 방향으로 한 칸 이동이 아닌 이동을 지원하지 않아야 한다")
    @ParameterizedTest
    @ValueSource(strings = {"a3", "b3", "c2", "c4", "f2", "f3", "f4", "f5"})
    public void canNotMove(String toStr) {
        Board board = createBoard("""
                ........
                ........
                ........
                ........
                ........
                ..n.....
                ........
                ........
                """);
        Position from = Position.of("c3");
        Position to = Position.of(toStr);
        Piece piece = board.get(from);

        Offset offset = Offset.between(from, to);
        BoardContext context = createBoardContext(board, from);
        boolean canMove = piece.canMove(offset, context);

        assertThat(piece).isInstanceOf(Knight.class);
        assertThat(canMove).isFalse();
    }

    @DisplayName("나이트의 중간 이동 경로에 기물이 있는 경우에도 이동을 지원해야 한다")
    @ParameterizedTest
    @ValueSource(strings = {"a2", "a4", "b1", "b5", "d1", "d5", "e2", "e4"})
    public void canNotMoveBlocked(String toStr) {
        Board board = createBoard("""
                ........
                ........
                ........
                ..B.B...
                .qKb....
                .Qnpr...
                .NRp....
                ..p.....
                """);
        Position from = Position.of("c3");
        Position to = Position.of(toStr);
        Piece piece = board.get(from);

        Offset offset = Offset.between(from, to);
        BoardContext context = createBoardContext(board, from);
        boolean canMove = piece.canMove(offset, context);

        assertThat(piece).isInstanceOf(Knight.class);
        assertThat(canMove).isTrue();
    }
}
