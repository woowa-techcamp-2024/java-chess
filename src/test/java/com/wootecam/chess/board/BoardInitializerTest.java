package com.wootecam.chess.board;

import static com.wootecam.chess.Fixture.createBoard;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("체스판 초기화 테스트")
class BoardInitializerTest {
    private Board board;
    private BoardInitializer boardInitializer = new BoardInitializer();

    @BeforeEach
    void setUp() {
        board = createBoard();
    }

    @Test
    void 체스판을_초기화하면_총_32개_기물들이_존재해야_한다() {
        boardInitializer.initialize(board);

        assertThat(board.size()).isEqualTo(32);
    }

    @Test
    void 체스판을_초기화하면_모든_기물들이_제자리에_배치되어_있어야_한다() {
        boardInitializer.initialize(board);

        assertThat(board.print()).isEqualTo("""
                RKBQKBKR
                PPPPPPPP
                ........
                ........
                ........
                ........
                pppppppp
                rkbqkbkr
                """);
    }
}