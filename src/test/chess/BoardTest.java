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

        Piece white = Pawn.createWhite();
        Position whitePosition = Position.of("b1");
        board.set(whitePosition, white);
        assertThat(board.get(whitePosition)).isEqualTo(white);
        assertThat(board.findPawns()).hasSize(1);
        assertThat(board.findPawns().get(0)).isEqualTo(white);

        Piece black = Pawn.createBlack();
        Position blackPosition = Position.of("a3");
        board.set(blackPosition, black);
        assertThat(board.get(blackPosition)).isEqualTo(black);
        assertThat(board.findPawns()).hasSize(2);
        assertThat(board.findPawns().get(1)).isEqualTo(black);
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

    private Board createBoard(String state) {
        assertThat(state).hasLineCount(Board.LENGTH);
        assertThat(state.lines()).allSatisfy(line -> assertThat(line).hasSize(Board.LENGTH));

        String[] lines = state.lines().toArray(String[]::new);
        Board board = new Board();
        for (int r = 0; r < Board.LENGTH; r++) {
            for (int c = 0; c < Board.LENGTH; c++) {
                char repr = lines[r].charAt(c);
                Piece piece = getPieceFromRepr(repr);
                board.set(Position.of(r, c), piece);
            }
        }
        return board;
    }

    private Piece getPieceFromRepr(char repr) {
        return switch (repr) {
            case 'P' -> Pawn.createBlack();
            case 'p' -> Pawn.createWhite();
            case 'N' -> Knight.createBlack();
            case 'n' -> Knight.createWhite();
            case 'B' -> Bishop.createBlack();
            case 'b' -> Bishop.createWhite();
            case 'R' -> Rook.createBlack();
            case 'r' -> Rook.createWhite();
            case 'Q' -> Queen.createBlack();
            case 'q' -> Queen.createWhite();
            case 'K' -> King.createBlack();
            case 'k' -> King.createWhite();
            default -> null;
        };
    }

}
