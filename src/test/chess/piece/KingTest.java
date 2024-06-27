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

public class KingTest {

    @Test
    @DisplayName("팩토리 메소드에 해당하는 킹이 생성되어야 한다")
    public void factory() {
        assertIsBlack(King.createBlack());
        assertIsWhite(King.createWhite());
    }

    @Test
    @DisplayName("생성자에 전달된 색의 킹이 생성되어야 한다")
    public void create() {
        assertIsBlack(new King(Piece.Color.BLACK));
        assertIsWhite(new King(Piece.Color.WHITE));
    }

    @Test
    @DisplayName("이동하지 않은 상태의 킹이 생성되어야 한다")
    public void moved() {
        assertNotMoved(new King(Piece.Color.BLACK));
    }

    @Test
    @DisplayName("킹을 이동한 경우 이동 여부가 참이어야 한다")
    public void move() {
        King pawn = new King(Piece.Color.BLACK);

        pawn.setMoved();

        assertIsMoved(pawn);
    }

    @DisplayName("킹이 십자가 및 대각선으로 한 칸 이동을 지원해야 한다")
    @ParameterizedTest
    @ValueSource(strings = {"b2", "b3", "b4", "c2", "c4", "d2", "d3", "d4"})
    public void canMove(String toStr) {
        Board board = createBoard("""
                ........
                ........
                ........
                ........
                ........
                ..k.....
                ........
                ........
                """);
        Position from = Position.of("c3");
        Position to = Position.of(toStr);
        Piece piece = board.get(from);

        Offset offset = Offset.between(from, to);
        BoardContext context = createBoardContext(board, from);
        boolean canMove = piece.canMove(offset, context);

        assertThat(piece).isInstanceOf(King.class);
        assertThat(canMove).isTrue();
    }

    @DisplayName("킹이 십자가 또는 대각선으로 한 칸이 아닌 이동을 지원하지 않아야 한다")
    @ParameterizedTest
    @ValueSource(strings = {"a1", "a3", "a5", "b1", "c1", "d1", "e1", "e2", "e3", "e4", "e5"})
    public void canNotMove(String toStr) {
        Board board = createBoard("""
                ........
                ........
                ........
                ........
                ........
                ..K.....
                ........
                ........
                """);
        Position from = Position.of("c3");
        Position to = Position.of(toStr);
        Piece piece = board.get(from);

        Offset offset = Offset.between(from, to);
        BoardContext context = createBoardContext(board, from);
        boolean canMove = piece.canMove(offset, context);

        assertThat(piece).isInstanceOf(King.class);
        assertThat(canMove).isFalse();
    }

}
