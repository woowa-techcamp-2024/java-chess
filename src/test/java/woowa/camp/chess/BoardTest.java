package woowa.camp.chess;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import woowa.camp.pieces.Color;
import woowa.camp.pieces.Piece;

public class BoardTest {

    private static final int KING_COUNT = 1;
    private static final int QUEEN_COUNT = 1;
    private static final int ROOK_COUNT = 2;
    private static final int KNIGHT_COUNT = 2;
    private static final int BISHOP_COUNT = 2;
    private static final int PAWN_COUNT = 8;

    Board board;
    Piece white;
    Piece black;

    @BeforeEach
    void setUp() {
        board = new Board();
        white = Piece.createWhitePawn();
        black = Piece.createBlackPawn();
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

        assertThat(board.getPiecesResult(Piece.PAWN, Color.PAWN_WHITE)).isEqualTo(expectedWhitePawnsResult);
        assertThat(board.getPiecesResult(Piece.PAWN, Color.PAWN_BLACK)).isEqualTo(expectedBlackPawnsResult);
    }

    @Test
    @DisplayName("[Success] 초기화한 Board가 가지고 있는 검은색 Pawn과 흰색 Pawn은 각각 8개이다.")
    void initialPawnGetPieceCount() {
        board.initialize();
        int expectedPawnsCount = Board.MAX_PAWN;
        verifyInitialPawnsCount(board, expectedPawnsCount);
    }

    private void verifyInitialPawnsCount(Board board, int expectedPawnsCount) {
        assertThat(board.getPiecesResult(Piece.PAWN, Color.PAWN_WHITE).length()).isEqualTo(expectedPawnsCount);
        assertThat(board.getPiecesResult(Piece.PAWN, Color.PAWN_BLACK).length()).isEqualTo(expectedPawnsCount);
    }

    @Test
    @DisplayName("[Success] 초기화한 Board의 크기는 8 x 8 이다.")
    void initialBoardGetPieceCount() {
        board.initialize();

        int resultBoardRowSize = board.getBoardRowSize();
        int resultBoardColSize = board.getBoardColSize();
        int expectedBoardRowSize = Board.MAX_ROW;
        int expectedBoardColSize = Board.MAX_COL;

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
    void initialGetPieceCount(String pieceName, Color color, int expectedCount) {
        board.initialize();
        Piece piece = Piece.createPiece(pieceName, color);
        int actualCount = board.getPieceCount(piece.getName(), color);
        assertThat(actualCount).isEqualTo(expectedCount);
    }

    static Stream<Arguments> providePiecesAndCounts() {
        return Stream.of(
                Arguments.of(Piece.KING, Color.KING_BLACK, KING_COUNT),
                Arguments.of(Piece.QUEEN, Color.QUEEN_BLACK, QUEEN_COUNT),
                Arguments.of(Piece.ROOK, Color.ROOK_BLACK, ROOK_COUNT),
                Arguments.of(Piece.KNIGHT, Color.KNIGHT_BLACK, KNIGHT_COUNT),
                Arguments.of(Piece.BISHOP, Color.BISHOP_BLACK, BISHOP_COUNT),
                Arguments.of(Piece.PAWN, Color.PAWN_BLACK, PAWN_COUNT),

                Arguments.of(Piece.KING, Color.KING_WHITE, KING_COUNT),
                Arguments.of(Piece.QUEEN, Color.QUEEN_WHITE, QUEEN_COUNT),
                Arguments.of(Piece.ROOK, Color.ROOK_WHITE, ROOK_COUNT),
                Arguments.of(Piece.KNIGHT, Color.KNIGHT_WHITE, KNIGHT_COUNT),
                Arguments.of(Piece.BISHOP, Color.BISHOP_WHITE, BISHOP_COUNT),
                Arguments.of(Piece.PAWN, Color.PAWN_WHITE, PAWN_COUNT)
        );
    }

}
