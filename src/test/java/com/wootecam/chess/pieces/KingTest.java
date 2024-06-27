package com.wootecam.chess.pieces;

import static com.wootecam.chess.Fixture.createBoard;
import static com.wootecam.chess.Fixture.createPosition;
import static org.assertj.core.api.Assertions.assertThat;

import com.wootecam.chess.Converter.DirectionConverter;
import com.wootecam.chess.board.Board;
import com.wootecam.chess.board.Position;
import com.wootecam.chess.move.Direction;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
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
    class 시작_위치와_움직일_위치가_주어질_때_킹이_움직여야_하는_방향을_반환한다 {

        @CsvSource({
                "b7, a8, SOUTHWEST",
                "b7, b8, WEST",
                "b7, c8, NORTHWEST",
                "b7, a7, SOUTH",
                "b7, c7, NORTH",
                "b7, a6, SOUTHEAST",
                "b7, b6, EAST",
                "b7, c6, NORTHEAST"
        })
        @ParameterizedTest
        void 킹이_움직여야_하는_방향을_반환한다(String fromPos, String toPos,
                                 @ConvertWith(DirectionConverter.class) Direction expected) {
            Piece king = Piece.createWhiteKing();
            Position from = createPosition(fromPos);
            Position to = createPosition(toPos);
            board.add(king, from);

            Optional<Direction> correctDirection = king.findCorrectDirection(from, to);

            assertThat(correctDirection).contains(expected);
        }
    }
}