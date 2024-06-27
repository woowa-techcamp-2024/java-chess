package chess.chess.piece;

import chess.chess.Board;
import chess.chess.BoardContext;
import chess.chess.Offset;
import chess.chess.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static chess.chess.BoardUtils.createBoard;
import static chess.chess.BoardUtils.createBoardContext;
import static chess.chess.piece.PieceUtils.*;
import static org.assertj.core.api.Assertions.assertThat;

public class BishopTest {

    @Test
    @DisplayName("팩토리 메소드에 해당하는 비숍이 생성되어야 한다")
    public void factory() {
        assertIsBlack(Bishop.createBlack());
        assertIsWhite(Bishop.createWhite());
    }

    @Test
    @DisplayName("생성자에 전달된 색의 비숍이 생성되어야 한다")
    public void create() {
        assertIsBlack(new Bishop(Piece.Color.BLACK));
        assertIsWhite(new Bishop(Piece.Color.WHITE));
    }

    @Test
    @DisplayName("이동하지 않은 상태의 비숍이 생성되어야 한다")
    public void moved() {
        assertNotMoved(new Bishop(Piece.Color.BLACK));
    }

    @Test
    @DisplayName("비숍을 이동한 경우 이동 여부가 참이어야 한다")
    public void move() {
        Bishop pawn = new Bishop(Piece.Color.BLACK);

        pawn.setMoved();

        assertIsMoved(pawn);
    }

    @DisplayName("비숍이 대각선 이동을 지원해야 한다")
    @ParameterizedTest
    @ValueSource(strings = {"a1", "b2", "d4", "e5", "f6", "g7", "h8", "b4", "a5", "d2", "e1"})
    public void canMove(String toStr) {
        Board board = createBoard("""
                ........
                ........
                ........
                ........
                ........
                ..b.....
                ........
                ........
                """);
        Position from = Position.of("c3");
        Position to = Position.of(toStr);
        Piece piece = board.get(from);

        Offset offset = Offset.between(from, to);
        BoardContext context = createBoardContext(board, from);
        boolean canMove = piece.canMove(offset, context);

        assertThat(piece).isInstanceOf(Bishop.class);
        assertThat(canMove).isTrue();
    }

    @DisplayName("비숍이 대각선이 아닌 이동을 지원하지 않아야 한다")
    @ParameterizedTest
    @ValueSource(strings = {"a3", "b3", "d3", "c1", "c2", "c4", "e7", "f4"})
    public void canNotMove(String toStr) {
        Board board = createBoard("""
                ........
                ........
                ........
                ........
                ........
                ..b.....
                ........
                ........
                """);
        Position from = Position.of("c3");
        Position to = Position.of(toStr);
        Piece piece = board.get(from);

        Offset offset = Offset.between(from, to);
        BoardContext context = createBoardContext(board, from);
        boolean canMove = piece.canMove(offset, context);

        assertThat(piece).isInstanceOf(Bishop.class);
        assertThat(canMove).isFalse();
    }

    @DisplayName("비숍의 중간 이동 경로에 기물이 있는 경우 이동을 지원하지 않아야 한다")
    @ParameterizedTest
    @ValueSource(strings = {"a1", "a5", "f6", "e7", "h8"})
    public void canNotMoveBlocked(String toStr) {
        Board board = createBoard("""
                ........
                ........
                ........
                ....p...
                .k......
                ..b.....
                .Q......
                ........
                """);
        Position from = Position.of("c3");
        Position to = Position.of(toStr);
        Piece piece = board.get(from);

        Offset offset = Offset.between(from, to);
        BoardContext context = createBoardContext(board, from);
        boolean canMove = piece.canMove(offset, context);

        assertThat(piece).isInstanceOf(Bishop.class);
        assertThat(canMove).isFalse();
    }
}
