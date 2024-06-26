package com.wootecam.chess;

import static com.wootecam.chess.utils.StringUtils.appendNewLine;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Nested
    class showBoard_메소드는 {

        @Nested
        class 보드를_생성하고_호출하면 {

            @Test
            void 현재_보드의_상태를_문자열로_반환한다() {
                // given
                String expectedResults = generateDefaultBoardResults();

                // when
                String currentBoardResults = board.showBoard();

                // then
                assertThat(currentBoardResults).isEqualTo(expectedResults);
            }

            private String generateDefaultBoardResults() {
                String blankRank = appendNewLine("........");

                return appendNewLine("RNBQKBNR") +
                        appendNewLine("PPPPPPPP") +
                        blankRank + blankRank + blankRank + blankRank +
                        appendNewLine("pppppppp") +
                        appendNewLine("rnbqkbnr");
            }
        }
    }

    @Nested
    class pieceCount_메소드는 {

        @Nested
        class 보드를_생성하고_호춣하면 {

            @Test
            void 현재_보드에_존재하는_기물의_갯수를_반환한다() {
                // given

                // when
                int count = board.pieceCount();

                // then
                assertThat(count).isEqualTo(32);
            }
        }
    }
}