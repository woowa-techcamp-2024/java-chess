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
@DisplayName("기물: 퀸 테스트")
class QueenTest {
    private Board board;

    @BeforeEach
    void setUp() {
        board = createBoard();
    }

    @Nested
    class 시작_위치와_움직일_위치가_주어질_때_퀸이_움직여야_하는_방향을_반환한다 {

        @CsvSource({
                "d5, a2, SOUTHEAST",
                "d5, b3, SOUTHEAST",
                "d5, c4, SOUTHEAST",
                "d5, e6, NORTHWEST",
                "d5, f7, NORTHWEST",
                "d5, g8, NORTHWEST",
                "d5, a8, SOUTHWEST",
                "d5, b7, SOUTHWEST",
                "d5, c6, SOUTHWEST",
                "d5, e4, NORTHEAST",
                "d5, f3, NORTHEAST",
                "d5, g2, NORTHEAST",
                "d5, h1, NORTHEAST",
                "d5, d1, EAST",
                "d5, d2, EAST",
                "d5, d3, EAST",
                "d5, d4, EAST",
                "d5, d6, WEST",
                "d5, d7, WEST",
                "d5, d8, WEST",
                "d5, a5, SOUTH",
                "d5, b5, SOUTH",
                "d5, c5, SOUTH",
                "d5, e5, NORTH",
                "d5, f5, NORTH",
                "d5, g5, NORTH",
                "d5, h5, NORTH"
        })
        @ParameterizedTest
        void 퀸이_움직여야_하는_방향을_반환한다(String fromPos, String toPos,
                                 @ConvertWith(DirectionConverter.class) Direction expected) {
            Piece queen = Piece.createWhiteQueen();
            Position from = createPosition(fromPos);
            Position to = createPosition(toPos);
            board.add(queen, from);

            Optional<Direction> correctDirection = queen.findCorrectDirection(from, to);

            assertThat(correctDirection).contains(expected);
        }
    }
}