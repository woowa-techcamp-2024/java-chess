package woowa.camp.chess;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static woowa.camp.chess.BoardConstants.MAX_BISHOP;
import static woowa.camp.chess.BoardConstants.MAX_COL;
import static woowa.camp.chess.BoardConstants.MAX_KING;
import static woowa.camp.chess.BoardConstants.MAX_KNIGHT;
import static woowa.camp.chess.BoardConstants.MAX_PAWN;
import static woowa.camp.chess.BoardConstants.MAX_QUEEN;
import static woowa.camp.chess.BoardConstants.MAX_ROOK;
import static woowa.camp.chess.BoardConstants.MAX_ROW;

import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import woowa.camp.pieces.Piece;
import woowa.camp.pieces.Piece.Color;
import woowa.camp.pieces.Piece.Type;

public class BoardTest {

    Board board;
    Piece white;
    Piece black;

    @BeforeEach
    void setUp() {
        board = new Board();
        white = Piece.createWhitePieceOf(Type.PAWN);
        black = Piece.createBlackPieceOf(Type.PAWN);
    }

    @Test
    @DisplayName("[Success] Pawn을 체스판에 추가할 수 있다.")
    void board_add_pawn() {
        board.add(white);
        verifyBoardGetPieceCount(board, 1);
        verifyGetPawn(board, 0, white);

        board.add(black);
        verifyBoardGetPieceCount(board, 2);
        verifyGetPawn(board, 1, black);
    }

    private void verifyBoardGetPieceCount(Board board, int expectedBoardSize) {
        assertThat(board.getPieceCount()).isEqualTo(expectedBoardSize);
    }

    private void verifyGetPawn(Board board, int findPawnIndex, Piece expectedPiece) {
        assertThat(board.getPawn(findPawnIndex)).isEqualTo(expectedPiece);
    }

    @Test
    @DisplayName("[Exception] 체스판의 Pawn을 찾을 때 올바르지 않은 범위이면, 예외가 발생한다.")
    void findOutOfRange() {
        board.add(white);
        board.add(black);

        int lowerBound = -1;
        int upperBound = board.getPieceCount();

        verifyOutOfRangeGetPawn(board, lowerBound, upperBound);
    }

    private void verifyOutOfRangeGetPawn(Board board, int lowerBound, int upperBound) {
        assertThatThrownBy(() -> board.getPawn(lowerBound)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> board.getPawn(upperBound)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("[Success] 초기화한 Board이 관리하고 있는 Pawn의 결과를 확인")
    void getPiecesResult() {
        board.initialize();
        String expectedWhitePawnsResult = "pppppppp";
        String expectedBlackPawnsResult = "PPPPPPPP";

        assertThat(board.getPiecesResult(Type.PAWN, Color.WHITE)).isEqualTo(expectedWhitePawnsResult);
        assertThat(board.getPiecesResult(Type.PAWN, Color.BLACK)).isEqualTo(expectedBlackPawnsResult);
    }

    @Test
    @DisplayName("[Success] 초기화한 Board가 가지고 있는 검은색 Pawn과 흰색 Pawn은 각각 8개이다.")
    void initialPawnGetPieceCount() {
        board.initialize();
        int expectedPawnsCount = MAX_PAWN.getCount();
        verifyInitialPawnsCount(board, expectedPawnsCount);
    }

    private void verifyInitialPawnsCount(Board board, int expectedPawnsCount) {
        assertThat(board.getPiecesResult(Type.PAWN, Color.WHITE).length()).isEqualTo(expectedPawnsCount);
        assertThat(board.getPiecesResult(Type.PAWN, Color.BLACK).length()).isEqualTo(expectedPawnsCount);
    }

    @Test
    @DisplayName("[Success] 초기화한 Board의 크기는 8 x 8 이다.")
    void initialBoardGetPieceCount() {
        board.initialize();

        int resultBoardRowSize = board.getBoardRowSize();
        int resultBoardColSize = board.getBoardColSize();
        int expectedBoardRowSize = MAX_ROW.getCount();
        int expectedBoardColSize = MAX_COL.getCount();

        assertThat(resultBoardRowSize).isEqualTo(expectedBoardRowSize);
        assertThat(resultBoardColSize).isEqualTo(expectedBoardColSize);
    }

    @Test
    @DisplayName("[Success] 초기화한 Board의 기물 위치 확인")
    void initialBoardStatus() {
        board.initialize();
        String result = board.showBoard();
        String expectedInitialBoardState = """
                RNBQKBNR
                PPPPPPPP
                ........
                ........
                ........
                ........
                pppppppp
                rnbqkbnr
                """;
        assertThat(result).isEqualTo(expectedInitialBoardState);
    }

    @ParameterizedTest
    @MethodSource("providePiecesAndCounts")
    @DisplayName("[Success] 초기화한 Board의 기물 개수 확인")
    void initialGetPieceCount(Type type, Color color, int expectedCount) {
        board.initialize();
        Piece piece = Piece.createPiece(type, color);
        int actualCount = board.getPieceCount(piece.getType(), color);
        assertThat(actualCount).isEqualTo(expectedCount);
    }

    static Stream<Arguments> providePiecesAndCounts() {
        return Stream.of(
                Arguments.of(Type.KING, Color.BLACK, MAX_KING.getCount()),
                Arguments.of(Type.QUEEN, Color.BLACK, MAX_QUEEN.getCount()),
                Arguments.of(Type.ROOK, Color.BLACK, MAX_ROOK.getCount()),
                Arguments.of(Type.KNIGHT, Color.BLACK, MAX_KNIGHT.getCount()),
                Arguments.of(Type.BISHOP, Color.BLACK, MAX_BISHOP.getCount()),
                Arguments.of(Type.PAWN, Color.BLACK, MAX_PAWN.getCount()),

                Arguments.of(Type.KING, Color.WHITE, MAX_KING.getCount()),
                Arguments.of(Type.QUEEN, Color.WHITE, MAX_QUEEN.getCount()),
                Arguments.of(Type.ROOK, Color.WHITE, MAX_ROOK.getCount()),
                Arguments.of(Type.KNIGHT, Color.WHITE, MAX_KNIGHT.getCount()),
                Arguments.of(Type.BISHOP, Color.WHITE, MAX_BISHOP.getCount()),
                Arguments.of(Type.PAWN, Color.WHITE, MAX_PAWN.getCount())
        );
    }

}
