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

public class RookTest {

    @Test
    @DisplayName("팩토리 메소드에 해당하는 룩이 생성되어야 한다")
    public void factory() {
        assertIsBlack(Rook.createBlack());
        assertIsWhite(Rook.createWhite());
    }

    @Test
    @DisplayName("생성자에 전달된 색의 룩이 생성되어야 한다")
    public void create() {
        assertIsBlack(new Rook(Piece.Color.BLACK));
        assertIsWhite(new Rook(Piece.Color.WHITE));
    }

    @Test
    @DisplayName("이동하지 않은 상태의 룩이 생성되어야 한다")
    public void moved() {
        assertNotMoved(new Rook(Piece.Color.BLACK));
    }

    @Test
    @DisplayName("룩을 이동한 경우 이동 여부가 참이어야 한다")
    public void move() {
        Rook pawn = new Rook(Piece.Color.BLACK);

        pawn.setMoved();

        assertIsMoved(pawn);
    }

    @DisplayName("룩이 십자가 이동을 지원해야 한다")
    @ParameterizedTest
    @ValueSource(strings = {"a3", "b3", "d3", "c1", "c2", "c4"})
    public void canMove(String toStr) {
        Board board = createBoard("""
                ........
                ........
                ........
                ........
                ........
                ..r.....
                ........
                ........
                """);
        Position from = Position.of("c3");
        Position to = Position.of(toStr);
        Piece piece = board.get(from);

        Offset offset = Offset.between(from, to);
        BoardContext context = createBoardContext(board, from);
        boolean canMove = piece.canMove(offset, context);

        assertThat(piece).isInstanceOf(Rook.class);
        assertThat(canMove).isTrue();
    }

    @DisplayName("룩이 십자가가 아닌 이동을 지원하지 않아야 한다")
    @ParameterizedTest
    @ValueSource(strings = {"a1", "b2", "d4", "e5", "f6", "g7", "h8", "b4", "a5", "d2", "e1", "e7", "f4"})
    public void canNotMove(String toStr) {
        Board board = createBoard("""
                ........
                ........
                ........
                ........
                ........
                ..R.....
                ........
                ........
                """);
        Position from = Position.of("c3");
        Position to = Position.of(toStr);
        Piece piece = board.get(from);

        Offset offset = Offset.between(from, to);
        BoardContext context = createBoardContext(board, from);
        boolean canMove = piece.canMove(offset, context);

        assertThat(piece).isInstanceOf(Rook.class);
        assertThat(canMove).isFalse();
    }

    @DisplayName("룩의 중간 이동 경로에 기물이 있는 경우 이동을 지원하지 않아야 한다")
    @ParameterizedTest
    @ValueSource(strings = {"a3", "c1", "g3", "h3"})
    public void canNotMoveBlocked(String toStr) {
        Board board = createBoard("""
                ........
                ........
                ........
                ........
                ........
                .pr..Q..
                ..k.....
                ........
                """);
        Position from = Position.of("c3");
        Position to = Position.of(toStr);
        Piece piece = board.get(from);

        Offset offset = Offset.between(from, to);
        BoardContext context = createBoardContext(board, from);
        boolean canMove = piece.canMove(offset, context);

        assertThat(piece).isInstanceOf(Rook.class);
        assertThat(canMove).isFalse();
    }
}
