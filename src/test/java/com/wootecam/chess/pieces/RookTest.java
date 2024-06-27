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
@DisplayName("기물: 룩 테스트")
class RookTest {
    private Board board;

    @BeforeEach
    void setUp() {
        board = createBoard();
    }

    @Nested
    class 시작_위치와_움직일_위치가_주어질_때_룩이_움직여야_하는_방향을_반환한다 {

        @CsvSource({
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
        void 룩이_움직여야_하는_방향을_반환한다(String fromPos, String toPos,
                                 @ConvertWith(DirectionConverter.class) Direction expected) {
            Piece rook = Piece.createWhiteRook();
            Position from = createPosition(fromPos);
            Position to = createPosition(toPos);
            board.add(rook, from);

            Optional<Direction> correctDirection = rook.findCorrectDirection(from, to);

            assertThat(correctDirection).contains(expected);
        }
    }
}