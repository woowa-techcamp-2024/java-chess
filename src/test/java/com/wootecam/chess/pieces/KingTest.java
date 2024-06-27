package com.wootecam.chess.pieces;

import static com.wootecam.chess.Fixture.createBoard;
import static com.wootecam.chess.Fixture.createPosition;
import static org.assertj.core.api.Assertions.assertThat;

import com.wootecam.chess.board.Board;
import com.wootecam.chess.board.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("기물: 킹 테스트")
class KingTest {
    private Board board;

    @BeforeEach
    void setUp() {
        board = createBoard();
    }

    @Nested
    class 킹은_모든_대각선과_상하좌우로_움직일_수_있다 {

        @CsvSource({
                "b7, a8",
                "b7, b8",
                "b7, c8",
                "b7, a7",
                "b7, c7",
                "b7, a6",
                "b7, b6",
                "b7, c6"
        })
        @ParameterizedTest
        void 모든_대각선과_상하좌우로_움직일_수_있다(String fromPos, String toPos) {
            Piece king = Piece.createWhiteKing();
            Position from = createPosition(fromPos);
            Position to = createPosition(toPos);
            board.add(king, from);

            boolean result = king.canMove(board, from, to);

            assertThat(result).isTrue();
        }

        @CsvSource({
                "b7, a8",
                "b7, b8",
                "b7, c8",
                "b7, a7",
                "b7, c7",
                "b7, a6",
                "b7, b6",
                "b7, c6"
        })
        @ParameterizedTest
        void 가려는_위치에_같은_편이_있다면_움직일_수_없다(String fromPos, String toPos) {
            Piece king = Piece.createWhiteKing();
            Piece queen = Piece.createWhiteQueen();

            Position from = createPosition(fromPos);
            Position to = createPosition(toPos);

            board.add(king, from);
            board.add(queen, to);

            boolean result = king.canMove(board, from, to);

            assertThat(result).isFalse();
        }
    }
}