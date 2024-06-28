package woowa.camp.chess;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static woowa.camp.chess.BoardConstants.INIT_PIECE_COUNT;
import static woowa.camp.chess.BoardConstants.MAX_BISHOP;
import static woowa.camp.chess.BoardConstants.MAX_COL;
import static woowa.camp.chess.BoardConstants.MAX_KING;
import static woowa.camp.chess.BoardConstants.MAX_KNIGHT;
import static woowa.camp.chess.BoardConstants.MAX_PAWN;
import static woowa.camp.chess.BoardConstants.MAX_QUEEN;
import static woowa.camp.chess.BoardConstants.MAX_ROOK;
import static woowa.camp.chess.BoardConstants.MAX_ROW;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import woowa.camp.pieces.Bishop;
import woowa.camp.pieces.Blank;
import woowa.camp.pieces.King;
import woowa.camp.pieces.Knight;
import woowa.camp.pieces.Pawn;
import woowa.camp.pieces.Piece;
import woowa.camp.pieces.Piece.Color;
import woowa.camp.pieces.Piece.Type;
import woowa.camp.pieces.Queen;
import woowa.camp.pieces.Rook;

public class BoardTest {

    Board board;
    Piece white;
    Piece black;

    @BeforeEach
    void setUp() {
        board = new Board();
        white = Pawn.createWhite();
        black = Pawn.createBlack();
    }

    @Nested
    @DisplayName("Describe_체스판에 기물을 추가하는 기능은")
    class MovePieceTest {

        @Test
        @DisplayName("[Success] 기물을 추가하면 기물 개수도 반영된다.")
        void board_add_pawn() {
            board.initialize();
            board.replace(white, Position.mapBy("a6"));
            verifyBoardGetPieceCount(board, INIT_PIECE_COUNT.getCount() + 1);
            verifyGetPiece(board, "a6", white);

            board.replace(black, Position.mapBy("b6"));
            verifyBoardGetPieceCount(board, INIT_PIECE_COUNT.getCount() + 2);
            verifyGetPiece(board, "b6", black);
        }

        private void verifyBoardGetPieceCount(Board board, int expectedBoardSize) {
            int pieceCount = board.getPieceCount();
            assertThat(pieceCount).isEqualTo(expectedBoardSize);
        }

        private void verifyGetPiece(Board board, String position, Piece expectedPiece) {
            assertThat(board.findPieceBy(position)).isEqualTo(expectedPiece);
        }

        @Test
        @DisplayName("[Success] 임의의 위치에 기물을 이동할 수 있어야 한다.")
        void move() {
            BoardGame boardGame = BoardGame.createWithInitialize(board);

            String sourcePosition = "b2";
            String targetPosition = "b3";
            boardGame.move(sourcePosition, targetPosition);
            assertThat(board.findPieceBy(sourcePosition)).isEqualTo(Blank.create());
            assertThat(board.findPieceBy(targetPosition)).isEqualTo(Pawn.createWhite());
        }

    }

    @Nested
    @DisplayName("Describe_체스판의 기물을 찾는 기능은")
    class GetPieceByTest {

        @Test
        @DisplayName("[Success] 체스위치에 대한 기물을 찾는다.")
        void findPawn() {
            board.initialize();
            assertThat(Rook.createBlack()).isEqualTo(board.findPieceBy("a8"));
            assertThat(Rook.createBlack()).isEqualTo(board.findPieceBy("h8"));
            assertThat(Rook.createWhite()).isEqualTo(board.findPieceBy("a1"));
            assertThat(Rook.createWhite()).isEqualTo(board.findPieceBy("h1"));
        }

        @Test
        @DisplayName("[Exception] 올바르지 않은 범위이면, 예외가 발생한다.")
        void findOutOfRange() {
            verifyOutOfRangeGetPiece(board, "`8");
            verifyOutOfRangeGetPiece(board, "a9");
            verifyOutOfRangeGetPiece(board, "h9");
            verifyOutOfRangeGetPiece(board, "i8");
            verifyOutOfRangeGetPiece(board, "i1");
            verifyOutOfRangeGetPiece(board, "h0");
            verifyOutOfRangeGetPiece(board, "a0");
            verifyOutOfRangeGetPiece(board, "`1");
        }

        private void verifyOutOfRangeGetPiece(Board board, String outOfPosition) {
            assertThatThrownBy(() -> board.findPieceBy(outOfPosition)).isInstanceOf(
                    IllegalArgumentException.class);
        }

    }

    @Nested
    @DisplayName("Describe_체스판을 초기화하는 기능은")
    class InitializeTest {

        @Test
        @DisplayName("[Success] 초기화한 Board가 관리하고 있는 Pawn의 결과를 확인")
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
            BoardGame boardGame = BoardGame.createWithInitialize(board);
            String result = boardGame.showBoard();
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
        void initialGetPieceCount(Piece piece, int expectedCount) {
            board.initialize();
            int actualCount = board.getPieceCount(piece.getType(), piece.getColor());
            assertThat(actualCount).isEqualTo(expectedCount);
        }

        static Stream<Arguments> providePiecesAndCounts() {
            return Stream.of(
                    Arguments.of(King.createBlack(), MAX_KING.getCount()),
                    Arguments.of(Queen.createBlack(), MAX_QUEEN.getCount()),
                    Arguments.of(Rook.createBlack(), MAX_ROOK.getCount()),
                    Arguments.of(Knight.createBlack(), MAX_KNIGHT.getCount()),
                    Arguments.of(Bishop.createBlack(), MAX_BISHOP.getCount()),
                    Arguments.of(Pawn.createBlack(), MAX_PAWN.getCount()),

                    Arguments.of(King.createWhite(), MAX_KING.getCount()),
                    Arguments.of(Queen.createWhite(), MAX_QUEEN.getCount()),
                    Arguments.of(Rook.createWhite(), MAX_ROOK.getCount()),
                    Arguments.of(Knight.createWhite(), MAX_KNIGHT.getCount()),
                    Arguments.of(Bishop.createWhite(), MAX_BISHOP.getCount()),
                    Arguments.of(Pawn.createWhite(), MAX_PAWN.getCount())
            );
        }
    }

    @Nested
    @DisplayName("Describe_체스판의 점수를 계산하는 기능은")
    class CalculateScoreTest {

        @Test
        @DisplayName("색상을 기준으로 현재 남아 있는 기물에 따라 점수를 계산한다.")
        void calculate() {
            BoardGame boardGame = BoardGame.createWithEmptyInitialize(board);

            addPiece("b6", Pawn.createBlack());
            addPiece("e6", Queen.createBlack());
            addPiece("b8", King.createBlack());
            addPiece("c8", Rook.createBlack());

            addPiece("f2", Pawn.createWhite());
            addPiece("g2", Pawn.createWhite());
            addPiece("e1", Rook.createWhite());
            addPiece("f1", King.createWhite());

            Assertions.assertEquals(15.0, boardGame.calculateScore(Color.BLACK), 0.01);
            Assertions.assertEquals(7.0, boardGame.calculateScore(Color.WHITE), 0.01);

            System.out.println(boardGame.showBoard());
        }

        private void addPiece(String position, Piece piece) {
            Position p = Position.mapBy(position);
            board.replace(piece, p);
        }
    }

    @Nested
    @DisplayName("Describe_기물을 정렬하는 기능은")
    class SortPieceTest {

        @Test
        @DisplayName("[Success] 남아있는 기물을 점수 기준으로 내림차순 정렬한다.")
        void descending() {
            board.initialize();
            List<Piece> result = board.getDescendingSortedPiecesFilterBy(Color.BLACK);
            assertThat(result).containsExactly(
                    Queen.createBlack(),
                    Rook.createBlack(),
                    Rook.createBlack(),
                    Bishop.createBlack(),
                    Bishop.createBlack(),
                    Knight.createBlack(),
                    Knight.createBlack(),
                    Pawn.createBlack(),
                    Pawn.createBlack(),
                    Pawn.createBlack(),
                    Pawn.createBlack(),
                    Pawn.createBlack(),
                    Pawn.createBlack(),
                    Pawn.createBlack(),
                    Pawn.createBlack(),
                    King.createBlack());
        }

        @Test
        @DisplayName("[Success] 남아있는 기물을 점수 기준으로 오름차순 정렬한다.")
        void ascending() {
            board.initialize();

            List<Piece> result = board.getAscendingSortedPiecesFilterBy(Color.BLACK);
            assertThat(result).containsExactly(
                    King.createBlack(),
                    Pawn.createBlack(),
                    Pawn.createBlack(),
                    Pawn.createBlack(),
                    Pawn.createBlack(),
                    Pawn.createBlack(),
                    Pawn.createBlack(),
                    Pawn.createBlack(),
                    Pawn.createBlack(),
                    Knight.createBlack(),
                    Knight.createBlack(),
                    Bishop.createBlack(),
                    Bishop.createBlack(),
                    Rook.createBlack(),
                    Rook.createBlack(),
                    Queen.createBlack());
        }
    }
}