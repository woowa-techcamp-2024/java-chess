package chess;

import chess.piece.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardTest {

    @Test
    @DisplayName("보드를 생성하고 기물이 추가되어야 한다.")
    public void create() {
        Board board = new Board();

        Piece white = Piece.createWhite(Pawn.class);
        board.set("b1", white);
        assertThat(board.get("b1")).isEqualTo(white);
        assertThat(board.findPawns()).hasSize(1);
        assertThat(board.findPawns().get(0)).isEqualTo(white);

        Piece black = Piece.createBlack(Pawn.class);
        board.set("a2", black);
        assertThat(board.get("a2")).isEqualTo(black);
        assertThat(board.findPawns()).hasSize(2);
        assertThat(board.findPawns().get(1)).isEqualTo(black);
    }

    @Test
    @DisplayName("보드의 toString 결과에 유니코드 체스말 형식으로 표시되어야 한다.")
    public void string() {
        Board board = new Board();
        board.set("a2", Piece.create(Pawn.class, Piece.Color.WHITE));
        board.set("b7", Piece.create(Pawn.class, Piece.Color.BLACK));
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
        assertThat(board.countPiece(Pawn.class, Piece.Color.BLACK)).isEqualTo(8);
        assertThat(board.countPiece(Rook.class, Piece.Color.WHITE)).isEqualTo(2);
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
    @DisplayName("체스판의 기물을 점수가 높은 순서로 반환해야 한다")
    public void sorted() {
        Board board = new Board();
        board.initialize();

        List<Piece> pieces = board.getPiecesInDescendingOrder(Piece.Color.WHITE);

        for (int i = 0; i < pieces.size() - 1; i++) {
            assertThat(pieces.get(i).value()).isGreaterThanOrEqualTo(pieces.get(i + 1).value());
        }
    }

    private Board createBoard(String state) {
        assertThat(state).hasLineCount(Board.LENGTH);
        assertThat(state.lines()).allSatisfy(line -> assertThat(line).hasSize(Board.LENGTH));

        String[] lines = state.lines().toArray(String[]::new);
        Board board = new Board();
        for (int r = 0; r < Board.LENGTH; r++) {
            for (int c = 0; c < Board.LENGTH; c++) {
                char repr = lines[r].charAt(c);
                Piece piece = getPieceFromRepr(repr);
                board.set(r, c, piece);
            }
        }
        return board;
    }

    private Piece getPieceFromRepr(char repr) {
        return switch (repr) {
            case 'P' -> Piece.createBlack(Pawn.class);
            case 'p' -> Piece.createWhite(Pawn.class);
            case 'N' -> Piece.createBlack(Knight.class);
            case 'n' -> Piece.createWhite(Knight.class);
            case 'B' -> Piece.createBlack(Bishop.class);
            case 'b' -> Piece.createWhite(Bishop.class);
            case 'R' -> Piece.createBlack(Rook.class);
            case 'r' -> Piece.createWhite(Rook.class);
            case 'Q' -> Piece.createBlack(Queen.class);
            case 'q' -> Piece.createWhite(Queen.class);
            case 'K' -> Piece.createBlack(King.class);
            case 'k' -> Piece.createWhite(King.class);
            default -> null;
        };
    }

}
