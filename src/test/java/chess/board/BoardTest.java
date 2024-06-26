package chess.board;

import chess.pieces.Piece;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static chess.pieces.Piece.Color;
import static chess.pieces.Piece.Type;
import static chess.utils.StringUtils.NEWLINE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.groups.Tuple.tuple;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardTest {

    @DisplayName("기물의 점수가 높은 순으로 정렬하여 반환할 수 있다.")
    @Test
    void sortPiecesByPoint() {
        // given
        Board board = new Board();

        // when
        List<Piece> pieces = board.sortPiecesByPoint(Color.WHITE, (o1, o2) -> Double.compare(o2.getPoint(), o1.getPoint()));

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

    @Test
    @DisplayName("포인트를 계산할 수 있다")
    void caculcatePoint() {
        Board board = new Board();
        board.initializeEmpty();

        addPiece("b6", Piece.createBlackPawn(), board);
        addPiece("e6", Piece.createBlackQueen(), board);
        addPiece("b8", Piece.createBlackKing(), board);
        addPiece("c8", Piece.createBlackRook(), board);

        addPiece("f2", Piece.createWhitePawn(), board);
        addPiece("g2", Piece.createWhitePawn(), board);
        addPiece("e1", Piece.createWhiteRook(), board);
        addPiece("f1", Piece.createWhiteKing(), board);

        assertEquals(15.0, board.caculcatePoint(Color.BLACK), 0.01);
        assertEquals(7.0, board.caculcatePoint(Color.WHITE), 0.01);

        System.out.println(board.showBoard());
    }


    @DisplayName("임의의 좌표에 Piece를 놓을 수 있다")
    @Test
    void move() {
        Board board = new Board();
        board.initializeEmpty();

        String position = "b5";
        Piece piece = Piece.createBlackRook();
        board.move(position, piece);

        assertEquals(piece, board.findPiece(position));
        System.out.println(board.showBoard());
    }

    @DisplayName("기존 보드를 빈 보드로 바꿀 수 있다.")
    @Test
    void initializeEmpty() {
        // given
        Board board = new Board();

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

        assertThat(board.showBoard()).isEqualTo(sb.toString());

    }

    @DisplayName("찾고자 하는 좌표를 반대로 보낸 경우 예외가 발생한다.")
    @Test
    void findPieceWithInvalidCoordinate() {
        // given
        Board board = new Board();
        String coordinate = "1e";

        // when & then
        assertThatThrownBy(() -> board.findPiece(coordinate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("좌표는 알파벳과 숫자의 순서로 이루어져야 합니다.");
    }

    @DisplayName("해당하는 좌표의 Piece를 반환한다")
    @Test
    void findPiece() {
        // given
        Board board = new Board();
        String coordinate = "a1";

        // when
        Piece piece = board.findPiece(coordinate);

        // then
        assertThat(piece).isNotNull()
                .extracting("color", "type")
                .contains(Color.WHITE, Type.ROOK);
    }

    @DisplayName("알파벳 좌표의 범위를 벗어난 경우 예외가 발생한다")
    @Test
    void findPieceWithInvalidAlphabetRange() {
        // given
        Board board = new Board();
        String coordinate = "i1";

        // when & then
        assertThatThrownBy(() -> board.findPiece(coordinate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("범위를 넘어선 좌표입니다.");
    }

    @DisplayName("올바르지 않은 형식의 좌표를 입력한 경우 예외가 발생한다")
    @Test
    void findPieceWithInvalidCoordinateFormat() {
        // given
        Board board = new Board();
        String coordinate = "i11";

        // when & then
        assertThatThrownBy(() -> board.findPiece(coordinate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("좌표는 2글자여야 합니다.");
    }
    @DisplayName("빈 좌표를 입력한 경우 예외가 발생한다")
    @Test
    void findPieceWithEmptyCoordinate() {
        // given
        Board board = new Board();
        String coordinate = "";

        // when & then
        assertThatThrownBy(() -> board.findPiece(coordinate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("좌표를 입력해주세요.");
    }

    @DisplayName("숫자 좌표의 범위를 벗어난 경우 예외가 발생한다")
    @Test
    void findPieceWithInvalidNumericRange() {
        // given
        Board board = new Board();
        String coordinate = "a9";

        // when & then
        assertThatThrownBy(() -> board.findPiece(coordinate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("범위를 넘어선 좌표입니다.");
    }

    @Test
    @DisplayName("보드를 생성할 수 있다")
    void create() {
        Board board = new Board();

        assertEquals(32, board.getTotalPieceCount());
        assertEquals(givenBoardPrint(), board.showBoard());
    }

    @DisplayName("해당하는 색상과 종류의 Piece 개수를 반환한다")
    @Test
    void getPieceCount() {
        // given
        Board board = new Board();

        // when
        int count = board.getPieceCount(Color.WHITE, Type.PAWN);

        // then
        assertEquals(8, count);
    }

    @DisplayName("해당하는 색상과 종류의 Piece 개수를 반환한다")
    @Test
    void getPieceCount2() {
        // given
        Board board = new Board();

        // when
        int count = board.getPieceCount(Color.WHITE, Type.ROOK);

        // then
        assertEquals(2, count);
    }

    private void addPiece(String position, Piece piece, Board board) {
        board.move(position, piece);
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
