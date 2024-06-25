package chess;

import static org.assertj.core.api.Assertions.assertThat;
import static pieces.Piece.Color.BLACK;
import static pieces.Piece.Color.WHITE;
import static pieces.Piece.PieceType.KING;
import static pieces.Piece.PieceType.PAWN;
import static pieces.Piece.PieceType.QUEEN;
import static pieces.Piece.PieceType.ROOK;
import static pieces.Piece.createPiece;
import static utils.StringUtils.appendNewLine;

import java.util.Comparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pieces.Piece;
import pieces.Piece.PieceType;

public class BoardTest {

    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
    }

    @Test
    public void create() {
        board.initialize();
        assertThat(board.totalPieceCount()).isEqualTo(32);
        String blankRank = appendNewLine(".".repeat(8));
        assertThat(
            appendNewLine("RNBQKBNR") + appendNewLine("P".repeat(8)) + blankRank + blankRank + blankRank + blankRank
                + appendNewLine("p".repeat(8)) + appendNewLine("rnbqkbnr")).isEqualTo(board.toString());
    }

    @Test
    public void findPiece() {
        board.initialize();

        assertThat(createPiece(BLACK, ROOK)).isEqualTo(board.getPieceByPosition("a8"));
        assertThat(createPiece(BLACK, ROOK)).isEqualTo(board.getPieceByPosition("h8"));
        assertThat(createPiece(WHITE, ROOK)).isEqualTo(board.getPieceByPosition("a1"));
        assertThat(createPiece(WHITE, ROOK)).isEqualTo(board.getPieceByPosition("h1"));
    }

    @Test
    public void getPieceCount() {
        board.initialize();

        assertThat(board.getPieceCountByPieceType(PieceType.KING)).isEqualTo(2);
        assertThat(board.getPieceCountByPieceType(PieceType.QUEEN)).isEqualTo(2);
        assertThat(board.getPieceCountByPieceType(ROOK)).isEqualTo(4);
        assertThat(board.getPieceCountByPieceType(PieceType.KNIGHT)).isEqualTo(4);
        assertThat(board.getPieceCountByPieceType(PieceType.BISHOP)).isEqualTo(4);
        assertThat(board.getPieceCountByPieceType(PieceType.PAWN)).isEqualTo(16);
    }

    @Test
    public void move() {
        board.initializeEmpty();

        String position = "b5";
        Piece piece = createPiece(BLACK, ROOK);
        board.move(position, piece);

        assertThat(piece).isEqualTo(board.getPieceByPosition(position));
        board.print();
    }

    @Test
    public void calculatePoint() {
        board.initializeEmpty();

        board.move("b6", createPiece(BLACK, PAWN));
        board.move("e6", createPiece(BLACK, QUEEN));
        board.move("b8", createPiece(BLACK, KING));
        board.move("c8", createPiece(BLACK, ROOK));

        board.move("f2", createPiece(WHITE, PAWN));
        board.move("g2", createPiece(WHITE, PAWN));
        board.move("e1", createPiece(WHITE, ROOK));
        board.move("f1", createPiece(WHITE, KING));

        assertThat(board.calculatePoint(BLACK)).isEqualTo(15.0);
        assertThat(board.calculatePoint(WHITE)).isEqualTo(7.0);

        board.print();
        System.out.println();
        System.out.println(
            board.sortPieces(BLACK, Comparator.comparing(piece -> piece.getPieceType().getDefaultPoint())));
        System.out.println(board.sortPieces(BLACK, Comparator.comparing(piece -> piece.getPieceType().getDefaultPoint())));
    }
}
