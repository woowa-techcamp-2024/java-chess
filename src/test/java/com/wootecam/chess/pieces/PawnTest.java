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
@DisplayName("기물: 폰 테스트")
class PawnTest {
    private Board board;

    @BeforeEach
    void setUp() {
        board = createBoard();
    }

    @Nested
    class 시작_위치와_움직일_위치가_주어질_때_폰이_움직여야_하는_방향을_반환한다 {

        @CsvSource({
                "d2, d3, WEST",
                "d2, c3, SOUTHWEST",
                "d2, e3, NORTHWEST",
        })
        @ParameterizedTest
        void 하얀색_폰이_움직여야_하는_방향을_반환한다(String fromPos, String toPos,
                                     @ConvertWith(DirectionConverter.class) Direction expected) {
            Piece pawn = Piece.createWhitePawn();
            Position from = createPosition(fromPos);
            Position to = createPosition(toPos);
            board.add(pawn, from);

            Optional<Direction> correctDirection = pawn.findCorrectDirection(from, to);

            assertThat(correctDirection).contains(expected);
        }

        @CsvSource({
                "d7, d6, EAST",
                "d7, c6, SOUTHEAST",
                "d7, e6, NORTHEAST",
        })
        @ParameterizedTest
        void 검은색_폰이_움직여야_하는_방향을_반환한다(String fromPos, String toPos,
                                     @ConvertWith(DirectionConverter.class) Direction expected) {
            Piece pawn = Piece.createBlackPawn();
            Position from = createPosition(fromPos);
            Position to = createPosition(toPos);
            board.add(pawn, from);

            Optional<Direction> correctDirection = pawn.findCorrectDirection(from, to);

            assertThat(correctDirection).contains(expected);
        }
    }
}