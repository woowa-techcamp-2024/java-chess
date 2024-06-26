package org.example.chess;

import static org.example.utils.StringUtils.appendNewLine;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.example.pieces.Piece.Color;
import org.example.pieces.Piece.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class BoardTest {

    private Board board;
    private static final int boardSize = 8;

    @BeforeEach
    public void setUpBoard() {
        board = new Board();
    }

    @Test
    public void create() throws Exception {
        assertEquals(32, board.nonEmptyPiece());
        String blankRank = appendNewLine("........");
        assertEquals(
            appendNewLine("♜♞♝♛♚♝♞♜") +
                appendNewLine("♟♟♟♟♟♟♟♟") +
                blankRank + blankRank + blankRank + blankRank +
                appendNewLine("♙♙♙♙♙♙♙♙") +
                appendNewLine("♖♘♗♕♔♗♘♖"),
            board.showBoard());

    }


    @ParameterizedTest
    @DisplayName("선택한 기물의 개수를 확인한다")
    @MethodSource("countByQueryArgument")
    public void countByQuery(
        Color color,
        Type type,
        int count
    ) {
        assertEquals(count, board.countByQuery(color, type));
    }

    private static Stream<Arguments> countByQueryArgument() {
        // 색상, 기물 종류, 개수
        return Stream.of(
            Arguments.arguments(Color.BLACK, Type.PAWN, 8),
            Arguments.arguments(Color.WHITE, Type.PAWN, 8),
            Arguments.arguments(Color.BLACK, Type.ROOK, 2),
            Arguments.arguments(Color.WHITE, Type.ROOK, 2),
            Arguments.arguments(Color.BLACK, Type.KNIGHT, 2),
            Arguments.arguments(Color.WHITE, Type.KNIGHT, 2),
            Arguments.arguments(Color.BLACK, Type.BISHOP, 2),
            Arguments.arguments(Color.WHITE, Type.BISHOP, 2),
            Arguments.arguments(Color.BLACK, Type.QUEEN, 1),
            Arguments.arguments(Color.WHITE, Type.QUEEN, 1),
            Arguments.arguments(Color.BLACK, Type.KING, 1),
            Arguments.arguments(Color.WHITE, Type.KING, 1),
            Arguments.arguments(Color.NO_COLOR, Type.NO_PIECE, 32)
        );
    }
}
