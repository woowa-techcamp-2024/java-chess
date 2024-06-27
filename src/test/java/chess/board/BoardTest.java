package chess.board;

import chess.pieces.Piece;
import chess.view.ChessView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static chess.pieces.Piece.Color;
import static chess.pieces.Piece.Type;
import static chess.utils.StringUtils.NEWLINE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardTest {

    private Board board;
    private ChessView chessView;

    @BeforeEach
    void setUp() {
        board = new Board();
        chessView = new ChessView(board);
    }

    @Test
    @DisplayName("기물을 특정 좌표로 이동할 수 있다")
    void moveToPosition() {
        // given
        String sourceCoordinate = "b2";
        String targetCoordinate = "b3";

        // when
        board.move(sourceCoordinate, targetCoordinate);

        // then
        Piece findPiece1 = board.findPiece(Coordinate.of(sourceCoordinate));
        assertThat(findPiece1)
                .extracting("color", "type")
                .contains(Color.NOCOLOR, Type.NO_PIECE);

        Piece findPiece2 = board.findPiece(Coordinate.of(targetCoordinate));
        assertThat(findPiece2)
                .extracting("color", "type")
                .contains(Color.WHITE, Type.PAWN);
    }

    @DisplayName("기물의 점수가 높은 순으로 정렬하여 반환할 수 있다.")
    @Test
    void sortPiecesByPoint() {
        // given

        // when
        List<Piece> pieces = board.sortPiecesByPoint(Color.WHITE, Board.SORT_DESCENDING);

        // then
        assertThat(pieces).extracting("point", "type")
                .containsExactly(tuple(9.0, Type.QUEEN),
                        tuple(5.0, Type.ROOK),
                        tuple(5.0, Type.ROOK),
                        tuple(3.0, Type.BISHOP),
                        tuple(3.0, Type.BISHOP),
                        tuple(2.5, Type.KNIGHT),
                        tuple(2.5, Type.KNIGHT),
                        tuple(1.0, Type.PAWN),
                        tuple(1.0, Type.PAWN),
                        tuple(1.0, Type.PAWN),
                        tuple(1.0, Type.PAWN),
                        tuple(1.0, Type.PAWN),
                        tuple(1.0, Type.PAWN),
                        tuple(1.0, Type.PAWN),
                        tuple(1.0, Type.PAWN),
                        tuple(0.0, Type.KING));
    }

    @DisplayName("기물의 점수를 오름차순으로 정렬하여 반환할 수 있다.")
    @Test
    void sortPiecesByPointAscending() {
        // given

        // when
        List<Piece> pieces = board.sortPiecesByPoint(Color.WHITE, Board.SORT_ASCENDING);

        // then
        assertThat(pieces).extracting("point", "type")
                .containsExactly(tuple(0.0, Type.KING),
                        tuple(1.0, Type.PAWN),
                        tuple(1.0, Type.PAWN),
                        tuple(1.0, Type.PAWN),
                        tuple(1.0, Type.PAWN),
                        tuple(1.0, Type.PAWN),
                        tuple(1.0, Type.PAWN),
                        tuple(1.0, Type.PAWN),
                        tuple(1.0, Type.PAWN),
                        tuple(2.5, Type.KNIGHT),
                        tuple(2.5, Type.KNIGHT),
                        tuple(3.0, Type.BISHOP),
                        tuple(3.0, Type.BISHOP),
                        tuple(5.0, Type.ROOK),
                        tuple(5.0, Type.ROOK),
                        tuple(9.0, Type.QUEEN));

    }


    @DisplayName("임의의 좌표에 Piece를 놓을 수 있다")
    @Test
    void move() {
        // given

        String coordinateStr = "b5";
        Coordinate coordinate = Coordinate.of(coordinateStr);
        Piece piece = Piece.createBlackRook();

        // when
        board.move(coordinate, piece);

        // then
        assertEquals(piece, board.findPiece(coordinate));
    }

    @DisplayName("기존 보드를 빈 보드로 바꿀 수 있다.")
    @Test
    void initializeEmpty() {
        // given

        // when
        board.initializeEmpty();

        // then
        String blankRank = "........";
        StringBuilder sb = new StringBuilder();
        sb.append(blankRank).append(NEWLINE);
        sb.append(blankRank).append(NEWLINE);
        sb.append(blankRank).append(NEWLINE);
        sb.append(blankRank).append(NEWLINE);
        sb.append(blankRank).append(NEWLINE);
        sb.append(blankRank).append(NEWLINE);
        sb.append(blankRank).append(NEWLINE);
        sb.append(blankRank);

        assertThat(chessView.printBoard()).isEqualTo(sb.toString());

    }

    @DisplayName("해당하는 좌표의 Piece를 반환한다")
    @Test
    void findPiece() {
        // given
        String coordinate = "a1";

        // when
        Piece piece = board.findPiece(Coordinate.of(coordinate));

        // then
        assertThat(piece).isNotNull()
                .extracting("color", "type")
                .contains(Piece.Color.WHITE, Piece.Type.ROOK);
    }

    @Test
    @DisplayName("보드를 생성할 수 있다")
    void create() {

        assertEquals(32, board.getTotalPieceCount());
        assertEquals(givenBoardPrint(), chessView.printBoard());
    }

    @DisplayName("해당하는 색상과 종류의 Piece 개수를 반환한다")
    @Test
    void getPieceCount() {
        // given

        // when
        int count = board.getPieceCount(Color.WHITE, Type.PAWN);

        // then
        assertEquals(8, count);
    }

    @DisplayName("해당하는 색상과 종류의 Piece 개수를 반환한다")
    @Test
    void getPieceCount2() {
        // given

        // when
        int count = board.getPieceCount(Color.WHITE, Type.ROOK);

        // then
        assertEquals(2, count);
    }

    private void addPiece(String coordniate, Piece piece, Board board) {
        board.move(Coordinate.of(coordniate), piece);
    }

    private String givenBoardPrint() {
        String blankRank = "........";
        StringBuilder sb = new StringBuilder();
            sb.append("RNBQKBNR").append(NEWLINE);
            sb.append("PPPPPPPP").append(NEWLINE);
            sb.append(blankRank).append(NEWLINE);
            sb.append(blankRank).append(NEWLINE);
            sb.append(blankRank).append(NEWLINE);
            sb.append(blankRank).append(NEWLINE);
            sb.append("pppppppp").append(NEWLINE);
            sb.append("rnbqkbnr");
        return sb.toString();
    }
}
