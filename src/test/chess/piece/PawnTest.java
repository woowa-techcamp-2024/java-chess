package chess.piece;

import chess.Board;
import chess.BoardContext;
import chess.Offset;
import chess.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static chess.BoardUtils.createBoard;
import static chess.BoardUtils.createBoardContext;
import static chess.piece.PieceUtils.*;
import static org.assertj.core.api.Assertions.assertThat;

public class PawnTest {

    @Test
    @DisplayName("팩토리 메소드에 해당하는 색의 폰이 생성되어야 한다")
    public void factory() {
        assertIsBlack(Pawn.createBlack());
        assertIsWhite(Pawn.createWhite());
    }

    @Test
    @DisplayName("생성자에 전달된 색의 폰이 생성되어야 한다")
    public void create() {
        assertIsBlack(new Pawn(Piece.Color.BLACK));
        assertIsWhite(new Pawn(Piece.Color.WHITE));
    }

    @Test
    @DisplayName("이동하지 않은 상태의 폰이 생성되어야 한다")
    public void moved() {
        assertNotMoved(new Pawn(Piece.Color.BLACK));
    }

    @Test
    @DisplayName("폰을 이동한 경우 이동 여부가 참이어야 한다")
    public void move() {
        Pawn pawn = new Pawn(Piece.Color.BLACK);

        pawn.setMoved();

        assertIsMoved(pawn);
    }

    @DisplayName("폰이 한 칸 앞으로 이동을 지원해야 한다")
    @ParameterizedTest
    @CsvSource({"b2,b3", "g7,g6"})
    public void canMove(String fromStr, String toStr) {
        Board board = createBoard("""
                ........
                ......P.
                ........
                ........
                ........
                ........
                .p......
                ........
                """);
        Position from = Position.of(fromStr);
        Position to = Position.of(toStr);
        Piece piece = board.get(from);

        Offset offset = Offset.between(from, to);
        BoardContext context = createBoardContext(board, from);
        boolean canMove = piece.canMove(offset, context);

        assertThat(piece).isInstanceOf(Pawn.class);
        assertThat(canMove).isTrue();
    }

    @DisplayName("폰이 움직이지 않은 경우 두 칸 앞으로 이동을 지원해야 한다")
    @ParameterizedTest
    @CsvSource({"b2,b4", "g7,g5"})
    public void canMove2(String fromStr, String toStr) {
        Board board = createBoard("""
                ........
                ......P.
                ........
                ........
                ........
                ........
                .p......
                ........
                """);
        Position from = Position.of(fromStr);
        Position to = Position.of(toStr);
        Piece piece = board.get(from);

        Offset offset = Offset.between(from, to);
        BoardContext context = createBoardContext(board, from);
        boolean canMove = piece.canMove(offset, context);

        assertThat(piece).isInstanceOf(Pawn.class);
        assertThat(canMove).isTrue();
    }

    @DisplayName("폰이 움직이지 않고 한 칸 앞에 기물이 있을 경우 두 칸 앞으로 이동을 지원하지 않아야 한다")
    @ParameterizedTest
    @CsvSource({"b2,b4", "g7,g5"})
    public void canNotMove2Blocked(String fromStr, String toStr) {
        Board board = createBoard("""
                ........
                ......P.
                ......k.
                ........
                ........
                .b......
                .p......
                ........
                """);
        Position from = Position.of(fromStr);
        Position to = Position.of(toStr);
        Piece piece = board.get(from);

        Offset offset = Offset.between(from, to);
        BoardContext context = createBoardContext(board, from);
        boolean canMove = piece.canMove(offset, context);

        assertThat(piece).isInstanceOf(Pawn.class);
        assertThat(canMove).isFalse();
    }

    @DisplayName("폰이 상대 말을 직접 잡는 경우 대각선 이동을 지원해야 한다")
    @ParameterizedTest
    @CsvSource({"b2,a3", "b2,c3", "g7,f6", "g7,h6"})
    public void canMoveDiag(String fromStr, String toStr) {
        Board board = createBoard("""
                ........
                ......P.
                .....p.p
                ........
                ........
                P.P.....
                .p......
                ........
                """);
        Position from = Position.of(fromStr);
        Position to = Position.of(toStr);
        Piece piece = board.get(from);

        Offset offset = Offset.between(from, to);
        BoardContext context = createBoardContext(board, from);
        boolean canMove = piece.canMove(offset, context);

        assertThat(piece).isInstanceOf(Pawn.class);
        assertThat(canMove).isTrue();
    }

    @DisplayName("폰이 옆에 있는 상대 말을 잡는 경우 대각선 이동을 지원해야 한다")
    @ParameterizedTest
    @CsvSource({"b2,a3", "b2,c3", "g7,f6", "g7,h6"})
    public void canMoveSide(String fromStr, String toStr) {
        Board board = createBoard("""
                ........
                .....pPp
                ........
                ........
                ........
                ........
                PpP.....
                ........
                """);
        Position from = Position.of(fromStr);
        Position to = Position.of(toStr);
        Piece piece = board.get(from);

        Offset offset = Offset.between(from, to);
        BoardContext context = createBoardContext(board, from);
        boolean canMove = piece.canMove(offset, context);

        assertThat(piece).isInstanceOf(Pawn.class);
        assertThat(canMove).isTrue();
    }
}
