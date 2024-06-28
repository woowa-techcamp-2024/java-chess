package com.wootecam.chess.board;

import static com.wootecam.chess.Fixture.createBoard;
import static com.wootecam.chess.board.Board.MAX_ROW;
import static org.assertj.core.api.Assertions.assertThat;

import com.wootecam.chess.pieces.Piece;
import java.util.List;
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

        List<List<Piece>> result = board.getState();

        assertThat(result.get(0)).containsExactly(
                Piece.createBlackRook(),
                Piece.createBlackKnight(),
                Piece.createBlackBishop(),
                Piece.createBlackQueen(),
                Piece.createBlackKing(),
                Piece.createBlackBishop(),
                Piece.createBlackKnight(),
                Piece.createBlackRook()
        );
        assertThat(result.get(1)).containsExactly(
                Piece.createBlackPawn(),
                Piece.createBlackPawn(),
                Piece.createBlackPawn(),
                Piece.createBlackPawn(),
                Piece.createBlackPawn(),
                Piece.createBlackPawn(),
                Piece.createBlackPawn(),
                Piece.createBlackPawn()
        );
        assertThat(result.get(MAX_ROW - 2)).containsExactly(
                Piece.createWhitePawn(),
                Piece.createWhitePawn(),
                Piece.createWhitePawn(),
                Piece.createWhitePawn(),
                Piece.createWhitePawn(),
                Piece.createWhitePawn(),
                Piece.createWhitePawn(),
                Piece.createWhitePawn()
        );
        assertThat(result.get(MAX_ROW - 1)).containsExactly(
                Piece.createWhiteRook(),
                Piece.createWhiteKnight(),
                Piece.createWhiteBishop(),
                Piece.createWhiteQueen(),
                Piece.createWhiteKing(),
                Piece.createWhiteBishop(),
                Piece.createWhiteKnight(),
                Piece.createWhiteRook()
        );
    }
}