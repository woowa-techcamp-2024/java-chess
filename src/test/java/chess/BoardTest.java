//package chess;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static pieces.Color.*;
//import static pieces.Piece.of;
//import static pieces.PieceType.*;
//import static utils.StringUtils.appendNewLine;
//
//import java.util.Comparator;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import pieces.Piece;
//import pieces.PieceType;
//
//public class BoardTest {
//
//    private Board board;
//
//    @BeforeEach
//    public void setUp() {
//        board = new Board();
//    }
//
//    @Test
//    public void create() {
//        board.initialize();
//        assertThat(board.totalPieceCount()).isEqualTo(32);
//        String blankRank = appendNewLine(".".repeat(8));
//        assertThat(
//            appendNewLine("RNBQKBNR") + appendNewLine("P".repeat(8)) + blankRank + blankRank + blankRank + blankRank
//                + appendNewLine("p".repeat(8)) + appendNewLine("rnbqkbnr")).isEqualTo(board.toString());
//    }
//
//    @Test
//    public void findPiece() {
//        board.initialize();
//
//        assertThat(of(BLACK, ROOK, Position.of(0, 0))).isEqualTo(board.getPieceByPosition("a8"));
//        assertThat(of(BLACK, ROOK, Position.of(0, 7))).isEqualTo(board.getPieceByPosition("h8"));
//        assertThat(of(WHITE, ROOK, Position.of(7, 0))).isEqualTo(board.getPieceByPosition("a1"));
//        assertThat(of(WHITE, ROOK, Position.of(7, 7))).isEqualTo(board.getPieceByPosition("h1"));
//    }
//
//    @Test
//    public void getPieceCount() {
//        board.initialize();
//
//        assertThat(board.getPieceCountByPieceType(KING)).isEqualTo(2);
//        assertThat(board.getPieceCountByPieceType(QUEEN)).isEqualTo(2);
//        assertThat(board.getPieceCountByPieceType(ROOK)).isEqualTo(4);
//        assertThat(board.getPieceCountByPieceType(PieceType.KNIGHT)).isEqualTo(4);
//        assertThat(board.getPieceCountByPieceType(PieceType.BISHOP)).isEqualTo(4);
//        assertThat(board.getPieceCountByPieceType(PAWN)).isEqualTo(16);
//    }
//
//    @Test
//    public void move() {
//        board.initializeEmpty();
//
//        String position = "b5";
//        Piece piece = of(BLACK, ROOK, Position.of(position));
//        board.move(position, piece);
//
//        assertThat(piece).isEqualTo(board.getPieceByPosition(position));
//        board.print();
//    }
//
//    @Test
//    public void calculatePoint() {
//        board.initializeEmpty();
//
//        board.move("b6", of(BLACK, PAWN, Position.of("b6")));
//        board.move("e6", of(BLACK, QUEEN, Position.of("e6")));
//        board.move("b8", of(BLACK, KING, Position.of("b8")));
//        board.move("c8", of(BLACK, ROOK, Position.of("c8")));
//
//        board.move("f2", of(WHITE, PAWN, Position.of("f2")));
//        board.move("g2", of(WHITE, PAWN, Position.of("g2")));
//        board.move("e1", of(WHITE, ROOK, Position.of("e1")));
//        board.move("f1", of(WHITE, KING, Position.of("f1")));
//
//        assertThat(board.calculatePoint(BLACK)).isEqualTo(15.0);
//        assertThat(board.calculatePoint(WHITE)).isEqualTo(7.0);
//
//        board.print();
//        System.out.println();
//        System.out.println(
//            board.sortPieces(BLACK,
//                Comparator.comparing((Piece piece) -> piece.getPieceType().getDefaultPoint()).reversed()));
//        System.out.println(
//            board.sortPieces(BLACK, Comparator.comparing(piece -> piece.getPieceType().getDefaultPoint())));
//    }
//
//    @Test
//    public void move2() throws Exception {
//        board.initialize();
//
//        String sourcePosition = "b2";
//        String targetPosition = "b3";
//        board.move(sourcePosition, targetPosition);
//        assertThat(Piece.of(BLANK, PieceType.BLANK, Position.of(sourcePosition))).isEqualTo(
//            board.getPieceByPosition(sourcePosition));
//        assertThat(Piece.of(WHITE, PAWN, Position.of(targetPosition))).isEqualTo(
//            board.getPieceByPosition(targetPosition));
//        board.print();
//    }
//}
