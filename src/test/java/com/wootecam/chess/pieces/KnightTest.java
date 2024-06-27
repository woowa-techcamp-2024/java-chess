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
@DisplayName("기물: 나이트 테스트")
class KnightTest {
    private Board board;

    @BeforeEach
    void setUp() {
        board = createBoard();
    }

    @Nested
    class 시작_위치와_움직일_위치가_주어질_때_나이트가_움직여야_하는_방향을_반환한다 {

        @CsvSource({
                "d4, c6, WWS",
                "d4, b5, SSW",
                "d4, b3, SSE",
                "d4, c2, EES",
                "d4, e2, EEN",
                "d4, f3, NNE",
                "d4, f5, NNW",
                "d4, e6, WWN",
        })
        @ParameterizedTest
        void 나이트가_움직여야_하는_방향을_반환한다(String fromPos, String toPos,
                                   @ConvertWith(DirectionConverter.class) Direction expected) {
            Piece knight = Piece.createWhiteKnight();
            Position from = createPosition(fromPos);
            Position to = createPosition(toPos);
            board.add(knight, from);

            Optional<Direction> correctDirection = knight.findCorrectDirection(from, to);

            assertThat(correctDirection).contains(expected);
        }
    }
}